package com.cooksys.twitterclone.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded 
    private Credentials credentials;
    
    @Embedded
    private Profile profile;
    
    @Column(nullable= false, updatable = false)
    @CreationTimestamp
    private Timestamp joined;
    
    @Column(nullable = false)
    private boolean deleted = false;
    
    @OneToMany(mappedBy = "author")
    private List<Tweet> tweets;
    
    @ManyToMany
    @JoinTable(
    		name = "user_likes",
    		joinColumns = @JoinColumn(name = "user_id"),
    		inverseJoinColumns = @JoinColumn(name = "tweet_id"))
    private List<Tweet> likedTweets;
    
    @ManyToMany(mappedBy = "followers")
    private List<User> following;
    
    @ManyToMany
    @JoinTable(name = "followers_following")
    private List<User> followers;
    
    @ManyToMany(mappedBy = "usersMentioned")
    private List<Tweet> mentions;

}
