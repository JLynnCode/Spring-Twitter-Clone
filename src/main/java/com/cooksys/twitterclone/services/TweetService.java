package com.cooksys.twitterclone.services;

import java.util.List;

import com.cooksys.twitterclone.entities.embeddable.Credentials;
import com.cooksys.twitterclone.model.ContextDto;
import com.cooksys.twitterclone.model.HashtagDto;
import com.cooksys.twitterclone.model.TweetRequestDto;
import com.cooksys.twitterclone.model.TweetResponseDto;
import com.cooksys.twitterclone.model.UserResponseDto;

public interface TweetService {

	List<TweetResponseDto> getActiveTweets();

	TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);

	TweetResponseDto getTweetById(Long id);

	TweetResponseDto deleteTweetById(Long id, Credentials credential);

	void likeTweetById(Long id, Credentials credential);

	TweetResponseDto replyTweetById(Long id, TweetRequestDto tweetRequestDto);

	TweetResponseDto repostTweetById(Long id, Credentials credential);

	List<HashtagDto> getTagForTweet(Long id);

	List<UserResponseDto> getLikeForTweet(Long id);

	ContextDto getContextForTweet(Long id);

	List<TweetResponseDto> getRepliesToTweetById(Long id);

	List<TweetResponseDto> getRepostOfTweetById(Long id);

	List<UserResponseDto> getMentionInTweetById(Long id);

	List<TweetResponseDto> getUserFeed(String username);

	List<TweetResponseDto> getUserTweets(String username);

	List<TweetResponseDto> getTweetsByMention(String username);

}
