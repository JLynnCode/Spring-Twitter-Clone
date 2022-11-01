package com.cooksystems.springassessmentsocialmedia.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 6711301937836286667L;
	
	private String message;

}
