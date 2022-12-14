package com.cooksys.twitterclone.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class Profile {

    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private String email;

    private String phone;
}