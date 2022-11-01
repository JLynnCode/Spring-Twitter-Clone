package com.cooksystems.springassessmentsocialmedia.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	private final TweetService tweetService;

	@GetMapping
	public List<UserResponseDto> getAllUsers() {
		return userService.getActiveUsers();
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
		return userService.createUser(userRequestDto);
	}
	
	@GetMapping("/@{username}")
    public UserResponseDto getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
	
    @PatchMapping("/@{username}")
    public UserResponseDto updateUser(@PathVariable String username, @RequestBody UserRequestDto userRequestDto) {
        return userService.updateUser(username, userRequestDto);
    }
    
    @DeleteMapping("/@{username}")
    public UserResponseDto deleteUser(@PathVariable String username, @RequestBody Credential credential) {
        return userService.deleteUser(username, credential);
    }
    
    @PostMapping("/@{username}/follow")
    public void followUser(@PathVariable String username, @RequestBody Credential credential) {
        userService.followUser(username, credential);
    }
    
    @PostMapping("/@{username}/unfollow")
    public void unFollowUser(@PathVariable String username, @RequestBody Credential credential) {
        userService.unfollowUser(username, credential);
    }
	
	
    @GetMapping("/@{username}/feed")
    public List<TweetResponseDto> getUserFeed(@PathVariable String username) {
        return tweetService.getUserFeed(username);
    }
    
    @GetMapping("/@{username}/tweets")
    public List<TweetResponseDto> getUserTweets(@PathVariable String username) {
        return tweetService.getUserTweets(username);
    }
    
    @GetMapping("/@{username}/mentions")
    public List<TweetResponseDto> getTweetsByMention(@PathVariable String username) {
        return tweetService.getTweetsByMention(username);
    }
    
    @GetMapping("/@{username}/followers")
    public List<UserResponseDto> getFollowers(@PathVariable String username) {
        return userService.getFollowers(username);
    }
    
    @GetMapping("/@{username}/following")
    public List<UserResponseDto> getFollowedUsers(@PathVariable String username) {
        return userService.getFollowedUsers(username);
    }
	

}
