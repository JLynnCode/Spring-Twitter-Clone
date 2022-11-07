package com.cooksys.twitterclone.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = -1755587379783387786L;
	
	private String message;

}
