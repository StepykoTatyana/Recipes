package com.example.Recipes.JsonTask.JacsonTask;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@JsonPropertyOrder({
        "likes",
        "comments",
        "createdDate", // here you can also use 'postedAt'
        "content",
})

public class Post {
    @JsonIgnore
    private int id;

    private Date createdDate;
    private String content;
    private int likes;
    private List<String> comments;

    public Post() {
    }

    @JsonProperty("createdDate")
    public String getReadableCreatedDate() {
        return (new SimpleDateFormat("dd-MM-yyyy")).format(createdDate);
    }
    @JsonCreator
    public Post(@JsonProperty("id") int id,
                @JsonProperty("createdDate") Date createdDate,
                @JsonProperty("content") String content,
                @JsonProperty("likes") int likes,
                @JsonProperty("comments") List<String> comments) {
        this.id = id;
        this.createdDate = createdDate;
        this.content = content;
        this.likes = likes;
        this.comments = comments;
    }

//    public Post(int id, Date createdDate, String content, int likes, List<String> comments) {
//        this.id = id;
//        this.createdDate = createdDate;
//        this.content = content;
//        this.likes = likes;
//        this.comments = comments;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
