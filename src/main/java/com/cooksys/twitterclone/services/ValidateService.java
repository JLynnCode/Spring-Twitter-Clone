package com.cooksys.twitterclone.services;

public interface ValidateService {

	boolean existUsername(String username);

	boolean existHashtag(String label);
	
	boolean isAvailableUsername(String username);
	
}
