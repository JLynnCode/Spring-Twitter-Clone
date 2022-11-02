package com.cooksys.twitterclone.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.model.HashtagDto;
import com.cooksys.twitterclone.model.TweetResponseDto;
import com.cooksys.twitterclone.services.HashtagService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HashTagServiceImpl implements HashtagService {@Override
	public List<HashtagDto> getAllHashtags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getTweetByTag(String label) {
		// TODO Auto-generated method stub
		return null;
	}

}
