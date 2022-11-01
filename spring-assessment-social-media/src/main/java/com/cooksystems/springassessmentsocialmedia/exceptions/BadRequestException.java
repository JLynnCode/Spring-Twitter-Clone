package com.cooksystems.springassessmentsocialmedia.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = -1755587379783387786L;
	
	private String message;

}
