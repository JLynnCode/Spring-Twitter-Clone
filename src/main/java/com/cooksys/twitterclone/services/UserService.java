package com.cooksys.twitterclone.services;

import java.util.List;

import com.cooksys.twitterclone.entities.embeddable.Credentials;
import com.cooksys.twitterclone.model.UserRequestDto;
import com.cooksys.twitterclone.model.UserResponseDto;

public interface UserService {

	List<UserResponseDto> getActiveUsers();

	UserResponseDto createUser(UserRequestDto userRequestDto);

	UserResponseDto getUserByUsername(String username);

	UserResponseDto updateUser(String username, UserRequestDto userRequestDto);

	UserResponseDto deleteUser(String username, Credentials credential);

	void followUser(String username, Credentials credential);

	void unfollowUser(String username, Credentials credential);

	List<UserResponseDto> getFollowedUsers(String username);

	List<UserResponseDto> getFollowers(String username);

}
