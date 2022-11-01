package com.cooksys.twitterclone.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserRequestDto {
	
	private CredentialsDto credentials;
	private ProfileDto profile;
}
