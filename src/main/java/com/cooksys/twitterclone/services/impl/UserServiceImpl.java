package com.cooksys.twitterclone.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.entities.Credentials;
import com.cooksys.twitterclone.entities.Tweet;
import com.cooksys.twitterclone.entities.User;
import com.cooksys.twitterclone.exceptions.BadRequestException;
import com.cooksys.twitterclone.exceptions.NotFoundException;
import com.cooksys.twitterclone.exceptions.UnauthorizedException;
import com.cooksys.twitterclone.mappers.TweetMapper;
import com.cooksys.twitterclone.mappers.UserMapper;
import com.cooksys.twitterclone.model.CredentialsDto;
import com.cooksys.twitterclone.model.TweetResponseDto;
import com.cooksys.twitterclone.model.UserRequestDto;
import com.cooksys.twitterclone.model.UserResponseDto;
import com.cooksys.twitterclone.repositories.TweetRepository;
import com.cooksys.twitterclone.repositories.UserRepository;
import com.cooksys.twitterclone.services.UserService;
import com.cooksys.twitterclone.services.ValidateService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final TweetMapper tweetMapper;
	private final TweetRepository tweetRepository;

	private final UserMapper userMapper;
	private final UserRepository userRepository;

	private final ValidateService validateService;

	public User userByCredentials(CredentialsDto credentials) {

		List<User> allUsers = userRepository.findAll();
		User newUser = new User();

		for (User u : allUsers) {

			if (u.getCredentials().getUsername().equals(credentials.getUsername())
					&& u.getCredentials().getPassword().equals(credentials.getPassword())) {

				newUser = u;
			}
		}

		return newUser;
	}

	private User authorizeCredential(Credentials credentials) {
		Optional<User> userOptional = userRepository.findByCredentials(credentials);
		if (userOptional.isEmpty() || userOptional.get().isDeleted())
			throw new UnauthorizedException("Invalid user!");
		return userOptional.get();
	}

	@Override
	public List<UserResponseDto> getActiveUsers() {

		List<User> allUsers = userRepository.findAll();
		List<UserResponseDto> activeUsers = new ArrayList<>();

		for (User user : allUsers) {

			if (!user.isDeleted()) {
				activeUsers.add(userMapper.entityToDto(user));
			}
		}

		return activeUsers;
	}

	@Override
	public UserResponseDto createUser(UserRequestDto userRequestDto) {

		User userToCreate = userMapper.dtoToEntity(userRequestDto);

		if (userToCreate.getCredentials() == null || userToCreate.getProfile() == null) {

			throw new BadRequestException("No content");
		}
		if (!validateService.isAvailableUsername(userToCreate.getCredentials().getUsername())) {

			throw new BadRequestException("Username is not available.");
		}
		if (validateService.existUsername(userToCreate.getCredentials().getUsername())) {

			if (userToCreate.isDeleted()) {

				userToCreate.setDeleted(false);

				return userMapper.entityToDto(userToCreate);
			}

			throw new BadRequestException("Username is already taken, please try another.");
		}

		if (userRequestDto.getProfile().getEmail() == null) {
			throw new BadRequestException("Email required");
		}

		User user = userMapper.dtoToEntity(userRequestDto);

		user.setCredentials(userToCreate.getCredentials());
		user.setProfile(userToCreate.getProfile());

		return userMapper.entityToDto(userRepository.saveAndFlush(user));
	}

	@Override
	public UserResponseDto getUserByUsername(String username) {

		User user = new User();
		List<User> allUsers = userRepository.findAll();

		for (User u : allUsers) {
			if (u.getCredentials().getUsername().equalsIgnoreCase(username)) {
				user = u;
			}
		}

		if (user.getId() == null || user.isDeleted()) {
			throw new NotFoundException("User not found. Please check username and try again.");
		}

		return userMapper.entityToDto(user);

	}

	@Override
	public UserResponseDto updateUser(String username, UserRequestDto userRequestDto) {

		User userUpdate = userMapper.dtoToEntity(userRequestDto);
		Optional<User> optionalUser = userRepository.findByCredentials_Username(username);

		if (optionalUser.isEmpty() || optionalUser.get().isDeleted()) {

			throw new NotFoundException("User not found. Please check username and try again.");
		}
		if (!userUpdate.getCredentials().equals(optionalUser.get().getCredentials())) {

			throw new UnauthorizedException("Provided user credentials do not match. Please validate and try again.");
		}

		optionalUser.get().setCredentials(userUpdate.getCredentials());
		optionalUser.get().setProfile(userUpdate.getProfile());

		return userMapper.entityToDto(userRepository.saveAndFlush(optionalUser.get()));
	}

	@Override
	public UserResponseDto deleteUser(String username, Credentials credential) {

		User userToDelete = authorizeCredential(credential);

		userToDelete.setDeleted(true);

		return userMapper.entityToDto(userRepository.saveAndFlush(userToDelete));
	}

	@Override
	public void followUser(String username, Credentials credential) {

		User user = userRepository.findByCredentials_Username(username).get();
		Optional<User> userToFollow = userRepository.findByCredentials(credential);
		List<User> following = user.getFollowing();

		if (userToFollow.isEmpty() || userToFollow.get().isDeleted()) {

			throw new NotFoundException("No such followable user exists. Please verify and try again.");
		}

		for (User u : following) {

			if (u.getFollowers().contains(user)) {

				throw new UnauthorizedException("Already following specified user.");
			}
		}

		following.add(userToFollow.get());
		userToFollow.get().getFollowers().add(user);

		List<User> users = new ArrayList<>();
		users.add(user);
		users.add(userToFollow.get());

		userRepository.saveAllAndFlush(users);
	}

	@Override
	public void unfollowUser(String username, Credentials credential) {

		User user = userRepository.findByCredentials_Username(username).get();
		Optional<User> userToFollow = userRepository.findByCredentials(credential);
		List<User> following = user.getFollowing();

		if (userToFollow.isEmpty() || userToFollow.get().isDeleted()) {

			throw new NotFoundException("No such followable user exists. Please verify and try again.");
		}

		for (User u : following) {

			if (u.getFollowers().contains(user)) {

				throw new UnauthorizedException("Already following specified user.");
			}
		}

		following.remove(userToFollow.get());
		userToFollow.get().getFollowers().remove(user);

		List<User> users = new ArrayList<>();
		users.remove(user);
		users.remove(userToFollow.get());

		userRepository.saveAllAndFlush(users);
	}

	@Override
	public List<UserResponseDto> getFollowedUsers(String username) {

		User user = userRepository.findByCredentials_Username(username).get();
		List<User> activeFollowers = new ArrayList<>();

		if (user.isDeleted()) {

			throw new NotFoundException("User not found. Please verify username and try again.");
		}

		for (User u : user.getFollowing()) {

			if (!u.isDeleted()) {

				activeFollowers.add(u);
			}
		}

		return userMapper.entitiesToDtos(activeFollowers);
	}

	@Override
	public List<UserResponseDto> getFollowers(String username) {

		User user = userRepository.findByCredentials_Username(username).get();
		List<User> activeFollowers = new ArrayList<>();

		if (user.isDeleted()) {

			throw new NotFoundException("User not found. Please verify username and try again.");
		}

		for (User u : user.getFollowers()) {

			if (!u.isDeleted()) {

				activeFollowers.add(u);
			}
		}

		return userMapper.entitiesToDtos(activeFollowers);
	}

	@Override
	public List<TweetResponseDto> getTweetsByMention(String username) {

		User user = userRepository.findByCredentials_Username(username).get();
		List<Tweet> allTweets = tweetRepository.findAll();
		List<Tweet> mentionedTweets = new ArrayList<>();

		for (Tweet t : allTweets) {

			if (t.getContent().contains("@" + username)) {

				t.getUsersMentioned().add(user);
				mentionedTweets.add(t);
			}
		}

		mentionedTweets = mentionedTweets.stream().filter(tweet -> !tweet.isDeleted())
				.sorted(Comparator.comparing(Tweet::getPosted)).toList();

		return tweetMapper.entitiesToDtos(mentionedTweets);
	}

	@Override
	public List<TweetResponseDto> getUserTweets(String username) {

		return tweetMapper.entitiesToDtos(userRepository.findByCredentials_Username(username).get().getTweets());
	}

	@Override
	public List<TweetResponseDto> getUserFeed(String username) {

		User user = userRepository.findByCredentials_Username(username).get();
		List<Tweet> tweets = user.getTweets();
		List<User> following = user.getFollowing();

		for (User u : following) {

			tweets.addAll(u.getTweets());
		}

		tweets = tweets.stream().filter(tweet -> !tweet.isDeleted()).sorted(Comparator.comparing(Tweet::getPosted))
				.toList();

		return tweetMapper.entitiesToDtos(tweets);
	}
}