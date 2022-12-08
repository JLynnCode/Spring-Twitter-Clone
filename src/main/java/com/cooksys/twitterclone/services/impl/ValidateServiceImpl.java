package com.cooksys.twitterclone.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.entities.Hashtag;
import com.cooksys.twitterclone.entities.User;
import com.cooksys.twitterclone.repositories.HashtagRepository;
import com.cooksys.twitterclone.repositories.UserRepository;
import com.cooksys.twitterclone.services.ValidateService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ValidateServiceImpl implements ValidateService{
	
	
    private final HashtagRepository hashtagRepository;
    private final UserRepository userRepository;

 
    
	@Override
	public boolean existUsername(String username) {
		// TODO Auto-generated method stub
		List<User> allUsers = userRepository.findAll();
		for (User user : allUsers) {
			if(user.getCredentials().getUsername().equals(username)) { 
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean existHashtag(String label) {
		// TODO Auto-generated method stub
		
		List<Hashtag> allHashtags = hashtagRepository.findAll();
		for (Hashtag hashtag : allHashtags) {
			if(hashtag.getLabel().equals("#" + label)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isAvailableUsername(String username) {
		// TODO Auto-generated method stub
		List<User> allUsers = userRepository.findAll();
		for (User user : allUsers) {
			if(user.getCredentials().getUsername().equals(username)) { 
				return false;
			}
		}
		return true;
		
	}

}
