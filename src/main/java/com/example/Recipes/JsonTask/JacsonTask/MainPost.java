package com.example.Recipes.JsonTask.JacsonTask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Date;

public class MainPost {
    public static void main(String[] args) throws JsonProcessingException {
        // Step 1
        Post post = new Post(
                1,
                new Date(),
                "I learned how to use jackson!",
                10,
                Arrays.asList("Well done!", "Great job!")
        );

// Step 2
        ObjectMapper objectMapper = new ObjectMapper();

// Step 3
        String postAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(post);

        System.out.println(postAsString);

        String inputJson = "{\"id\":1,\"createdDate\":1654027200000,\"content\":\"I learned how to use jackson!\",\"likes\":10,\"comments\":[\"Well done!\",\"Great job!\"]}\n";

        ObjectMapper objectMapper1 = new ObjectMapper();
        Post post1 = objectMapper1.readValue(inputJson, Post.class);
        System.out.println(post1.getComments());
    }
}
