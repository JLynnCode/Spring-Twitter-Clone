package com.cooksystems.springassessmentsocialmedia.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3397997900239121574L;

}
