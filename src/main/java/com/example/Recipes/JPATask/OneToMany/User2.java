package com.example.Recipes.JPATask.OneToMany;

import javax.persistence.*;
import java.util.*;

@Entity
public class User2 {

    @Id
    private long id;

    @OneToMany
    @JoinColumn(name = "user_id", nullable = false)
    private List<Tweet2> tweets = new ArrayList<>();
}

@Entity
class Tweet2 {

    @Id
    @Column(name = "tweet_id")
    private long id;
}