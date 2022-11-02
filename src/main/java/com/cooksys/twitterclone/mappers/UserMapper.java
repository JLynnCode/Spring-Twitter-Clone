package com.cooksys.twitterclone.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooksys.twitterclone.entities.User;
import com.cooksys.twitterclone.model.UserRequestDto;
import com.cooksys.twitterclone.model.UserResponseDto;

@Mapper(componentModel = "spring", uses = {ProfileMapper.class, CredentialsMapper.class})
public interface UserMapper {
	
	User dtoToEntity(UserRequestDto userRequestDto);
	
	List<UserResponseDto> entitiesToDtos(List<User> users);
	
	@Mapping(source = "credentials.username", target = "username")
	UserResponseDto entityToDto(User user);
}
