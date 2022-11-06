package com.cooksys.twitterclone.mappers;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

import com.cooksys.twitterclone.entities.Tweet;
import com.cooksys.twitterclone.model.TweetRequestDto;
import com.cooksys.twitterclone.model.TweetResponseDto;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface TweetMapper {

	TweetResponseDto entityToDto(Tweet tweet);

	List<TweetResponseDto> entitiesToDtos(List<Tweet> tweet);

	Tweet dtoToEntity(TweetRequestDto dto);

	TweetRequestDto entityToRequestDto(Tweet tweet);

	Tweet responseDtoToEntity(TweetResponseDto tweetResponseDto);

	List<TweetResponseDto> entitiesToDtos(Set<Tweet> tweet);

}
