package com.example.Recipes.JPATask.ManyToOne;

import javax.persistence.*;
import java.util.*;

@Entity
public class Tweet1 {

    @Id
    @Column(name = "tweet_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User1 user1;
}

@Entity
class User1 {

    @Id
    private long id;

    @OneToMany(mappedBy = "user1")
    private List<Tweet1> tweets = new ArrayList<>();

}