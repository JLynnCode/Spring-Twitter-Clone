package com.cooksys.twitterclone.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProfileDto {
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	
	private String email;
}
