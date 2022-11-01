package com.cooksys.twitterclone.model;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TweetResponseDto {
	
	private Long id;
	
	private User author;
	private Tweet inReplyTo;
	private Tweet repostOf;
	
	private Timestamp timestamp;
	private boolean deleted;
	private String content;
}
