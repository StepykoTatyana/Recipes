package com.example.Recipes.JPATask.OneToOne;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.*;

@Entity
public class User3 {

    @Id
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDetails user_details;

}

@Entity
class UserDetails {

    private long user_id;

    @Id
    private long user_detail_id;

    private String name;
    private String location;
    private String email;

}