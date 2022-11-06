package com.cooksys.twitterclone.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.entities.Credentials;
import com.cooksys.twitterclone.entities.Hashtag;
import com.cooksys.twitterclone.entities.Tweet;
import com.cooksys.twitterclone.entities.User;
import com.cooksys.twitterclone.exceptions.BadRequestException;
import com.cooksys.twitterclone.exceptions.NotFoundException;
import com.cooksys.twitterclone.exceptions.UnauthorizedException;
import com.cooksys.twitterclone.mappers.CredentialsMapper;
import com.cooksys.twitterclone.mappers.HashtagMapper;
import com.cooksys.twitterclone.mappers.TweetMapper;
import com.cooksys.twitterclone.mappers.UserMapper;
import com.cooksys.twitterclone.model.ContextDto;
import com.cooksys.twitterclone.model.CredentialsDto;
import com.cooksys.twitterclone.model.HashtagDto;
import com.cooksys.twitterclone.model.TweetRequestDto;
import com.cooksys.twitterclone.model.TweetResponseDto;
import com.cooksys.twitterclone.model.UserResponseDto;
import com.cooksys.twitterclone.repositories.HashtagRepository;
import com.cooksys.twitterclone.repositories.TweetRepository;
import com.cooksys.twitterclone.repositories.UserRepository;
import com.cooksys.twitterclone.services.TweetService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TweetServiceImpl implements TweetService {

	private final TweetMapper tweetMapper;
	private final TweetRepository tweetRepository;
	private final HashtagMapper hashtagMapper;
	private final UserMapper userMapper;
	private final UserRepository userRepository;
	private final HashtagRepository hashtagRepository;
	private final CredentialsMapper credentialsMapper;
	
	private Tweet tweetById(Long id) {
		Optional<Tweet> tweetOptional = tweetRepository.findById(id);
		if (tweetOptional.isEmpty() || tweetOptional.get().isDeleted())
			throw new BadRequestException("Tweet not found");
		return tweetOptional.get();
	}

	public User userByCredentials(CredentialsDto credentials) {
		List<User> allUsers = userRepository.findAll();
		User newUser = new User();
		for (User user : allUsers) {
			if (user.getCredentials().getUsername().equals(credentials.getUsername())
					&& user.getCredentials().getPassword().equals(credentials.getPassword())) {
				newUser = user;
			}
		}
		return newUser;
	}

	@Override
	public List<TweetResponseDto> getActiveTweets() {

		// TODO Auto-generated method stub
		List<Tweet> allTweets = new ArrayList<Tweet>();
		for (Tweet tweet : tweetRepository.findAll()) {
			if (!tweet.isDeleted()) {
				allTweets.add(tweet);
			}
		}
		return tweetMapper.entitiesToDtos(allTweets);

	}

	@Override
	public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto) {
		// TODO Auto-generated method stub
		if (tweetRequestDto.getContent() == (null)) {
			throw new BadRequestException("No content");
		}
		Tweet tweetToPost = tweetMapper.dtoToEntity(tweetRequestDto);
		List<User> allUsers = userRepository.findAll();
		for (User user : allUsers) {
			if (user.getCredentials().getUsername().equals(tweetRequestDto.getCredentials().getUsername())) {
				tweetToPost.setAuthor(user);
			}
		}
		if (tweetToPost.getContent() != (null)) {
			for (User user : allUsers) {
				if (tweetToPost.getContent().contains("@" + user.getCredentials().getUsername())) {
					tweetToPost.getUsersMentioned().add(user);
				}
				if (tweetToPost.getContent().contains("#")) {
					Pattern MY_PATTERN = Pattern.compile("#(\\S+)");
					Matcher mat = MY_PATTERN.matcher(tweetToPost.getContent());
					List<String> strs = new ArrayList<String>();
					while (mat.find()) {
						strs.add(mat.group(1));
					}
					List<Hashtag> userHashtags = new ArrayList<>();
					for (String s : strs) {
						Hashtag hashtag = new Hashtag();
						hashtag.setLabel(s);
						userHashtags.add(hashtag);
					}
					hashtagRepository.saveAll(userHashtags);
					tweetToPost.getHashtags().addAll(userHashtags);
				}
			}
		}
		return tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweetToPost));
	}

	@Override
	public TweetResponseDto getTweetById(Long id) {
		// TODO Auto-generated method stub
		return tweetMapper.entityToDto(tweetById(id));
	}

	@Override
	public TweetResponseDto deleteTweetById(Long id, Credentials credential) {
		// TODO Auto-generated method stub
		Tweet tweet = tweetById(id);
		if (tweet.equals(null) || tweet.isDeleted()) {
			throw new NotFoundException("Tweet not found");
		}
		if (!tweet.getAuthor().getCredentials().getUsername().equals(credential.getUsername())
				&& tweet.getAuthor().getCredentials().getPassword().equals(credential.getPassword())) {
			throw new UnauthorizedException("User not authorized");
		}
		tweet.setDeleted(true);
		tweetRepository.save(tweet);
		return tweetMapper.entityToDto(tweet);
	}

	@Override
	public void likeTweetById(Long id, Credentials credential) {
		// TODO Auto-generated method stub
		Tweet tweet = tweetById(id);
		User user = userRepository.findByCredentials(credential).get();
		tweet.getLikes().add(user);
		user.getLikedTweets().add(tweet);
		userRepository.save(user);
		tweetRepository.save(tweet);
	}

	@Override
	public TweetResponseDto replyTweetById(Long id, TweetRequestDto tweetRequestDto) {
		// TODO Auto-generated method stub
		Tweet tweet = tweetById(id);
		Tweet tweetReply = new Tweet();
		User user = userByCredentials(tweetRequestDto.getCredentials());
		tweetReply.setContent(tweetRequestDto.getContent());
		tweetReply.setAuthor(user);
		tweetReply.setInReplyTo(tweet);
		tweet.getReplies().add(tweetReply);
		tweetRepository.save(tweet);
		return tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweetReply));
	}

	@Override
	public TweetResponseDto repostTweetById(Long id, Credentials credential) {
		// TODO Auto-generated method stub
		Tweet tweet = tweetById(id);
		Tweet tweetRepost = new Tweet();
		User user = userByCredentials(credentialsMapper.entityToDto(credential));
		tweetRepost.setAuthor(user);
		tweetRepost.setRepostOf(tweet);
		tweet.getReposts().add(tweetRepost);
		tweetRepository.save(tweet);
		return tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweetRepost));
	}

	@Override
	public List<HashtagDto> getTagForTweet(Long id) {
		// TODO Auto-generated method stub
		List<Hashtag> hashtags = tweetById(id).getHashtags();
		return hashtagMapper.entitiesToDtos(hashtags);
	}

	@Override
	public List<UserResponseDto> getLikeForTweet(Long id) {
		// TODO Auto-generated method stub
		Tweet tweet = tweetById(id);
		List<User> likes = tweet.getLikes();
		return userMapper.entitiesToDtos(likes);
	}

	@Override
	public ContextDto getContextForTweet(Long id) {
		// TODO Auto-generated method stub
		ContextDto contextDto = new ContextDto();
		Tweet contextTweet = tweetById(id);
		if (contextTweet.isDeleted() || contextTweet == null) {
			throw new NotFoundException("Not Found");
		}
		Tweet beforeTweet = contextTweet.getInReplyTo();
		List<Tweet> beforeTweets = new ArrayList<>();
		List<Tweet> after = contextTweet.getReplies();
		List<Tweet> afterTweets = new ArrayList<>();
		afterTweets.addAll(after);
		while (beforeTweet != null) {
			beforeTweets.add(beforeTweet);
			beforeTweet = beforeTweet.getInReplyTo();
		}
		for (Tweet tweet : after) {
			if (tweet.getReplies() != (null)) {
				afterTweets.addAll(tweet.getReplies());
			}
		}
		for (Tweet tweet : beforeTweets) {
			if (tweet.isDeleted()) {
				beforeTweets.remove(tweet);
			}
		}
		for (Tweet tweet : afterTweets) {
			if (tweet.isDeleted()) {
				afterTweets.remove(tweet);
			}
		}
		contextDto.setBefore(tweetMapper.entitiesToDtos(beforeTweets));
		contextDto.setTarget(tweetMapper.entityToDto(contextTweet));
		contextDto.setAfter(tweetMapper.entitiesToDtos(afterTweets));

		return contextDto;
	}

	@Override
	public List<TweetResponseDto> getRepliesToTweetById(Long id) {
		// TODO Auto-generated method stub
		Tweet tweetReplies = tweetById(id);
		List<Tweet> replyList = 
				tweetRepository.findAll()
				.stream()
				.filter(tweet -> tweetReplies.getReplies().contains(tweet) && !tweet.isDeleted())
				.toList();
		return tweetMapper.entitiesToDtos(replyList);

	}

	@Override
	public List<TweetResponseDto> getRepostOfTweetById(Long id) {
		// TODO Auto-generated method stub
		Tweet tweet = tweetById(id);
		List<Tweet> reposts = tweet.getReposts();
		for (Tweet t : reposts) {
			if(tweet.isDeleted()) {
				reposts.remove(t);
			}
		}
		return tweetMapper.entitiesToDtos(tweet.getReposts());
	}

	@Override
	public List<UserResponseDto> getMentionInTweetById(Long id) {
		// TODO Auto-generated method stub
		Tweet tweet = tweetById(id);
		List<User> allUsers = userRepository.findAll();
		List<User> mentionedUsers = new ArrayList<>();
		for(User u : allUsers) {
			if(tweet.getContent().contains("@" + u.getCredentials().getUsername()) && !u.isDeleted()) {
				u.getMentions().add(tweet);
				tweet.getUsersMentioned().add(u);
				mentionedUsers.add(u);
			}
		}
		return userMapper.entitiesToDtos(mentionedUsers);
	}
}
