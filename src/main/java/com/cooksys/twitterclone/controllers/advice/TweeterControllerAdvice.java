package com.cooksys.twitterclone.controllers.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cooksys.twitterclone.model.ErrorDto;
import com.cooksys.twitterclone.exceptions.BadRequestException;
import com.cooksys.twitterclone.exceptions.NotFoundException;
import com.cooksys.twitterclone.exceptions.UnauthorizedException;

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
