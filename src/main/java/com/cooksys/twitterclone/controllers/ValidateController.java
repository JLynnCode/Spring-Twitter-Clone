package com.cooksys.twitterclone.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.twitterclone.services.ValidateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {

	private final ValidateService validateService;

	@GetMapping("/username/exists/@{username}")
	public boolean existUsername(@PathVariable String username) {
		return validateService.existUsername(username);
	}

	@GetMapping("/username/available/@{username}")
	public boolean availableUsername(@PathVariable String username) {
		return !validateService.existUsername(username);
	}

	@GetMapping("/tag/exists/{label}")
	public Boolean existHashtag(@PathVariable String label) {
		return validateService.existHashtag(label);
	}

}
