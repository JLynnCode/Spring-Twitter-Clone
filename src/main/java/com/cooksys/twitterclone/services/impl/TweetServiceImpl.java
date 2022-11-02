package com.cooksys.twitterclone.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.entities.embeddable.Credentials;
import com.cooksys.twitterclone.model.ContextDto;
import com.cooksys.twitterclone.model.HashtagDto;
import com.cooksys.twitterclone.model.TweetRequestDto;
import com.cooksys.twitterclone.model.TweetResponseDto;
import com.cooksys.twitterclone.model.UserResponseDto;
import com.cooksys.twitterclone.services.TweetService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TweetServiceImpl implements TweetService {@Override
	public List<TweetResponseDto> getActiveTweets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto getTweetById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto deleteTweetById(Long id, Credentials credential) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void likeTweetById(Long id, Credentials credential) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TweetResponseDto replyTweetById(Long id, TweetRequestDto tweetRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto repostTweetById(Long id, Credentials credential) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashtagDto> getTagForTweet(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserResponseDto> getLikeForTweet(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContextDto getContextForTweet(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getRepliesToTweetById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getRepostOfTweetById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserResponseDto> getMentionInTweetById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getUserFeed(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getUserTweets(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getTweetsByMention(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
