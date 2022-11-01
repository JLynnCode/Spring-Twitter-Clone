package com.cooksys.twitterclone.model;

import java.sql.Timestamp;

import com.cooksys.twitterclone.entities.Profile;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {
	
	private String username;
	private Profile profile;
	private Timestamp joined;
}
