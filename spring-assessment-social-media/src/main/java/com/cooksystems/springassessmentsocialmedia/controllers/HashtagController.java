package com.cooksystems.springassessmentsocialmedia.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class HashtagController {

	@GetMapping
	public List<HashtahResponseDto> getAllHashtags() {
		return hashtagService.getAllHashtags();
	}

	@GetMapping("/{label}")
	public List<TweetResponseDto> getTweetByTag(@PathVariable String label) {
		return hashtagService.getTweetByTag(label);
	}

}
