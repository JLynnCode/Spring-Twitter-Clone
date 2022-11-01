package com.cooksys.socialmedia.entities.embeddable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class Credentials {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

}
