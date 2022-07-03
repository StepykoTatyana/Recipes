package com.example.Recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class RecipesService {

    @Autowired
    RecipesRepository repository;

    public RecipesRepository getRepository() {
        return repository;
    }

    public void setRepository(RecipesRepository repository) {
        this.repository = repository;
    }

    public List<Recipes> getAllRecipes() {
        List<Recipes> recipes = new ArrayList<Recipes>();
        for (Recipes recipes2 : repository.findAll()) {
            recipes.add(recipes2);
        }
        return recipes;
    }

    public ResponseEntity<?> getRecipesById(long id) {
        try {
            Recipes recipes = repository.findById(id).get();
            return new ResponseEntity<>(new Recipes(recipes.getName(), recipes.getDescription(),
                    recipes.getCategory(), recipes.getDate(),
                    recipes.getIngredients(), recipes.getDirections(), recipes.getEmail()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public Recipes getRecipesByIdForAll(long id) {
        Recipes recipes = repository.findById(id).get();
        return new Recipes(recipes.getName(),
                recipes.getDescription(),
                recipes.getCategory(),
                recipes.getDate(),
                recipes.getIngredients(),
                recipes.getDirections(),
                recipes.getEmail());


    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }


    public void saveToRepository(String name, String description, String category, LocalDateTime date,
                                 List<String> ingredients, List<String> directions, String email) {
        repository.save(new Recipes(name, description, category, date, ingredients, directions, email));
    }


}
