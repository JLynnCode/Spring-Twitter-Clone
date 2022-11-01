package com.cooksys.twitterclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.twitterclone.entities.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

}
