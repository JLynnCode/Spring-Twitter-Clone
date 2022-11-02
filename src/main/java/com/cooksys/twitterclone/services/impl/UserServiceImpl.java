package com.cooksys.twitterclone.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.entities.Credentials;
import com.cooksys.twitterclone.entities.User;
import com.cooksys.twitterclone.mappers.UserMapper;
import com.cooksys.twitterclone.model.UserRequestDto;
import com.cooksys.twitterclone.model.UserResponseDto;
import com.cooksys.twitterclone.repositories.UserRepository;
import com.cooksys.twitterclone.services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	
	private final UserMapper userMapper;
	private final UserRepository userRepository;
	
	@Override
	public List<UserResponseDto> getActiveUsers() {
		
		List<User> allUsers = userRepository.findAll();
		List<UserResponseDto> activeUsers = new ArrayList<>();
		
		for(User user : allUsers){
			
			if(!user.isDeleted()){
				activeUsers.add(userMapper.entityToDto(user));
			}
		}
		
		return activeUsers;
	}

	@Override
	public UserResponseDto createUser(UserRequestDto userRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponseDto getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponseDto updateUser(String username, UserRequestDto userRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponseDto deleteUser(String username, Credentials credential) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void followUser(String username, Credentials credential) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unfollowUser(String username, Credentials credential) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserResponseDto> getFollowedUsers(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserResponseDto> getFollowers(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
