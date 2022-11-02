package com.cooksys.twitterclone.services;

public interface ValidateService {

	boolean existUsername(String username);

	Boolean existHashtag(String label);
	
}
