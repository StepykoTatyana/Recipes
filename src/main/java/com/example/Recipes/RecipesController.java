package com.example.Recipes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RecipesController {

    final Map<Integer, RecipeList> recipeMap = new HashMap<>();
    ResponseId responseId = new ResponseId();
    int id = 1;

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<?> getAddressBook(@PathVariable int id) {

        if (recipeMap.containsKey(id)) {
            return new ResponseEntity<>(recipeMap.get(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/recipe/new")
    public ResponseId postAddressBook(@RequestBody RecipeList recipe) {
        RecipeList recipeList = new RecipeList(recipe.getName(), recipe.getDescription(), recipe.getIngredients(), recipe.getDirections());
        recipeMap.put(id, recipeList);
        responseId.setId(id);
        id++;
        return responseId;
    }
}
