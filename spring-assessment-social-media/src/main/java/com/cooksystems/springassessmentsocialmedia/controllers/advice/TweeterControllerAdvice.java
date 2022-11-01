package com.cooksystems.springassessmentsocialmedia.controllers.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cooksystems.springassessmentsocialmedia.exceptions.BadRequestException;
import com.cooksystems.springassessmentsocialmedia.exceptions.NotFoundException;
import com.cooksystems.springassessmentsocialmedia.exceptions.UnauthorizedException;

@ControllerAdvice(basePackages = { "com.cooksystems.springassessmentsocialmedia.controllers" })
@ResponseBody
public class TweeterControllerAdvice {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorDto handleBadRequestException(HttpServletRequest request, NotFoundException notFoundException) {
        return new ErrorDto(notFoundException.getMessage());
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorDto handleBadRequestException(HttpServletRequest request, BadRequestException badRequestException) {
        return new ErrorDto(badRequestException.getMessage());
    }
	
	  @ResponseStatus(HttpStatus.UNAUTHORIZED)
	    @ExceptionHandler(UnauthorizedException.class)
	    public ErrorDto handleBadRequestException(HttpServletRequest request, UnauthorizedException unauthorizedException) {
	        return new ErrorDto(unauthorizedException.getMessage());
	    }

}
