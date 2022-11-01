package com.cooksys.twitterclone.model;

import com.cooksys.twitterclone.entities.Tweet;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ContextDto {
	
	private Tweet target;
	private Tweet before;
	private Tweet after;
}
