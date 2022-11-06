package com.cooksys.twitterclone.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.twitterclone.entities.Credentials;
import com.cooksys.twitterclone.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByCredentials_Username(String username);

	Optional<User> findByCredentials(Credentials credential);
}
