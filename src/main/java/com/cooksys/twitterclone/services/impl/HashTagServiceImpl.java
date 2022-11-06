package com.cooksys.twitterclone.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.entities.Tweet;
import com.cooksys.twitterclone.exceptions.NotFoundException;
import com.cooksys.twitterclone.mappers.HashtagMapper;
import com.cooksys.twitterclone.mappers.TweetMapper;
import com.cooksys.twitterclone.model.HashtagDto;
import com.cooksys.twitterclone.model.TweetResponseDto;
import com.cooksys.twitterclone.repositories.HashtagRepository;
import com.cooksys.twitterclone.repositories.TweetRepository;
import com.cooksys.twitterclone.services.HashtagService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HashTagServiceImpl implements HashtagService {
	
	private final HashtagMapper hashtagMapper;
    private final HashtagRepository hashtagRepository;
    private final TweetMapper tweetMapper;
    private final TweetRepository tweetRepository;
	
	@Override
	public List<HashtagDto> getAllHashtags() {
		// TODO Auto-generated method stub
		return hashtagMapper.entitiesToDtos(hashtagRepository.findAll());
	}

	@Override
	public List<TweetResponseDto> getTweetByTag(String label) {
		// TODO Auto-generated method stub
		
		List<Tweet> tweetsToReturn = new ArrayList<>();
		List<Tweet> allTweets = tweetRepository.findAll();
		for(Tweet t : allTweets) {
			if(!t.isDeleted() && t.getContent() != null && t.getContent().contains("#" + label)){
				tweetsToReturn.add(t);
			}
		}
		if(tweetsToReturn.isEmpty()) {
			throw new NotFoundException("Not found");
		}
		tweetsToReturn.sort(Comparator.comparing(Tweet::getPosted).reversed());
		return tweetMapper.entitiesToDtos(tweetsToReturn);
	}
	
}
