package com.cooksys.twitterclone.services;

import java.util.List;

import com.cooksys.twitterclone.model.HashtagDto;
import com.cooksys.twitterclone.model.TweetResponseDto;

public interface HashtagService {

	List<HashtagDto> getAllHashtags();

	List<TweetResponseDto> getTweetByTag(String label);
	
}
