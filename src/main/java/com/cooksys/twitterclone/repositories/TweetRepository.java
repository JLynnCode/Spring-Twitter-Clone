package com.cooksys.twitterclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.twitterclone.entities.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

}
