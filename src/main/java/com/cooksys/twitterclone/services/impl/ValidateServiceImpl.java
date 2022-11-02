package com.cooksys.twitterclone.services.impl;

import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.services.ValidateService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ValidateServiceImpl implements ValidateService{@Override
	public boolean existUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean existHashtag(String label) {
		// TODO Auto-generated method stub
		return null;
	}

}
