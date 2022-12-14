package com.cooksys.twitterclone.mappers;

import org.mapstruct.Mapper;

import com.cooksys.twitterclone.entities.Credentials;
import com.cooksys.twitterclone.model.CredentialsDto;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {
	
	CredentialsDto entityToDto(Credentials credentials);
	
}
