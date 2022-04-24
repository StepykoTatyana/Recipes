package com.example.Recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;


@RestController
public class RecipesController {

    @Autowired
    RecipesService recipesService;

    ResponseId responseId = new ResponseId();
    int id = 1;

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable int id) {
        return recipesService.getRecipesById(id);
    }

    @PostMapping("/api/recipe/new")
    public ResponseId postRecipes(@Validated @RequestBody Recipes recipe) {
        recipesService.saveToRepository(recipe.getName(), recipe.getDescription(),
                recipe.getCategory(), LocalDateTime.now(),
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


    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<?> putRecipes(@PathVariable long id, @Validated @RequestBody Recipes recipe) {
        try {
            Recipes recipes = recipesService.repository.findById(id).get();
            recipes.setName(recipe.getName());
            recipes.setCategory(recipe.getCategory());
            recipes.setDate(LocalDateTime.now());
            recipes.setDescription(recipe.getDescription());
            recipes.setIngredients(recipe.getIngredients());
            recipes.setDirections(recipe.getDirections());
            recipesService.repository.save(recipes);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/api/recipe/search")
    public ResponseEntity<?> getRecipeSearchCategory(@RequestParam(required = false) String category,
                                                     @RequestParam(required = false) String name) {
        if (category != null & name == null) {
            System.out.println(recipesService.getRepository().findByNameContainingIgnoreCase(category, null));
            return new ResponseEntity<>(recipesService.getRepository().findByNameContainingIgnoreCase(category, null), HttpStatus.OK);
        }
        else if(category == null & name != null) {
            System.out.println(recipesService.getRepository().findByNameContainingIgnoreCase(name, null));
            return new ResponseEntity<>(recipesService.getRepository().findByNameContainingIgnoreCase(name, null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
