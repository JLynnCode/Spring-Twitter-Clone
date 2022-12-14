package com.cooksys.twitterclone.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.twitterclone.entities.Credentials;
import com.cooksys.twitterclone.model.ContextDto;
import com.cooksys.twitterclone.model.HashtagDto;
import com.cooksys.twitterclone.model.TweetRequestDto;
import com.cooksys.twitterclone.model.TweetResponseDto;
import com.cooksys.twitterclone.model.UserResponseDto;
import com.cooksys.twitterclone.services.TweetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {
	
	private final TweetService tweetService;
	
	@GetMapping
    public List<TweetResponseDto> getAllTweets() {
        return tweetService.getActiveTweets();
    }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TweetResponseDto createTweet(@RequestBody TweetRequestDto tweetRequestDto) {
		return tweetService.createTweet(tweetRequestDto);
	}

	@GetMapping("/{id}")
	public TweetResponseDto getTweedById(@PathVariable Long id) {
		return tweetService.getTweetById(id);
	}

	@DeleteMapping("/{id}")
	public TweetResponseDto deleteTweetById(@PathVariable Long id, @RequestBody Credentials credential) {
		return tweetService.deleteTweetById(id, credential);
	}

	@PostMapping("/{id}/like")
	public void likeTweetById(@PathVariable Long id, @RequestBody Credentials credential) {
		tweetService.likeTweetById(id, credential);
	}

	@PostMapping("/{id}/reply")
	@ResponseStatus(HttpStatus.CREATED)
	public TweetResponseDto replyTweetById(@PathVariable Long id, @RequestBody TweetRequestDto tweetRequestDto) {
		return tweetService.replyTweetById(id, tweetRequestDto);
	}

	@PostMapping("/{id}/repost")
	@ResponseStatus(HttpStatus.CREATED)
	public TweetResponseDto repostTweetById(@PathVariable Long id, @RequestBody Credentials credential) {
		return tweetService.repostTweetById(id, credential);
	}

	@GetMapping("/{id}/tags")
	public List<HashtagDto> getTagForTweet(@PathVariable Long id) {
		return tweetService.getTagForTweet(id);
	}

	@GetMapping("/{id}/likes")
	public List<UserResponseDto> getLikeForTweet(@PathVariable Long id) {
		return tweetService.getLikeForTweet(id);
	}

	@GetMapping("/{id}/context")
	public ContextDto getContextForTweet(@PathVariable Long id) {
		return tweetService.getContextForTweet(id);
	}

	@GetMapping("/{id}/replies")
	public List<TweetResponseDto> getReplyToTweetById(@PathVariable Long id) {
		return tweetService.getRepliesToTweetById(id);
	}

	@GetMapping("/{id}/reposts")
	public List<TweetResponseDto> getRepostOfTweetById(@PathVariable Long id) {
		return tweetService.getRepostOfTweetById(id);
	}

	@GetMapping("/{id}/mentions")
	public List<UserResponseDto> getMentionInTweetById(@PathVariable Long id) {
		return tweetService.getMentionInTweetById(id);
	}

}
