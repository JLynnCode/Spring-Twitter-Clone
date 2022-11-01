package com.cooksys.twitterclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.twitterclone.entities.embeddable.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}
