package com.example.Recipes;

import lombok.Data;

import java.util.List;

@Data
public class RecipeList {

    private String name;
    private String description;
    private List<String> ingredients;
    private List<String> directions;

    public RecipeList(String name, String description, List<String> ingredients, List<String> directions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getDirections() {
        return directions;
    }
}


