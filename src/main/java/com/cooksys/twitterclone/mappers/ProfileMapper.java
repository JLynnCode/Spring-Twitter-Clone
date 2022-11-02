package com.cooksys.twitterclone.mappers;

import org.mapstruct.Mapper;

import com.cooksys.twitterclone.entities.Profile;
import com.cooksys.twitterclone.model.ProfileDto;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
	
	Profile dtoToEntity(ProfileDto profileRequestDto);
	
	ProfileDto entityToDto(Profile profile);
}
