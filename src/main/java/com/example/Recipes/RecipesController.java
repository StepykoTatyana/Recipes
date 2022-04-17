package com.example.Recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RecipesController {

    @Autowired
    RecipesService recipesService;


    //final Map<Integer, Recipes> recipeMap = new HashMap<>();
    ResponseId responseId = new ResponseId();
    int id = 1;

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable int id) {
        return recipesService.getRecipesById(id);
    }

    @PostMapping("/api/recipe/new")
    public ResponseId postRecipes(@Validated @RequestBody Recipes recipe) {

        recipesService.saveToRepository(recipe.getName(), recipe.getDescription(),
                recipe.getIngredients(), recipe.getDirections());
        responseId.setId(id);
        id++;
        return responseId;
    }


    @GetMapping("/api/recipes")
    public ResponseEntity<?> getRecipes() {
        return new ResponseEntity<>(recipesService.getAllRecipes(), HttpStatus.OK);
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<?> deleteRecipes(@PathVariable int id) {
        if (recipesService.getRecipesById(id).getStatusCode() == HttpStatus.OK) {
            recipesService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
