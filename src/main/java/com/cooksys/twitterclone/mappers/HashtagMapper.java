package com.cooksys.twitterclone.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.twitterclone.entities.Hashtag;
import com.cooksys.twitterclone.model.HashtagDto;

@Mapper(componentModel = "spring", uses = { HashtagMapper.class })
public interface HashtagMapper {

	HashtagDto entityToDto(Hashtag hashtag);

	List<HashtagDto> entitiesToDtos(List<Hashtag> hashtags);

	Hashtag dtoToEntity(HashtagDto dto);

}
