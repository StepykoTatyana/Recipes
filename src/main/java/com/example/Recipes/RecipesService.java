package com.example.Recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        //List<RecipesFromRequest> recipes1 = new ArrayList<RecipesFromRequest>();
        for (Recipes recipes2 : repository.findAll()) {
            recipes.add(recipes2);
        }
        //repository.findAll().forEach(recipes1 -> recipes.add(recipes1));
        return recipes;
    }

    public ResponseEntity<?> getRecipesById(long id) {
        try {
            Recipes recipes = repository.findById(id).get();

//            RecipesFromRequest recipes1 = new RecipesFromRequest(recipes.getName(),
//                    recipes.getDescription(),
//                    recipes.getIngredients(),
//                    recipes.getDirections());


            return new ResponseEntity<>(new Recipes(recipes.getName(), recipes.getDescription(),
                    recipes.getIngredients(), recipes.getDirections()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public Recipes getRecipesByIdForAll(long id) {
        Recipes recipes = repository.findById(id).get();
        return new Recipes(recipes.getName(),
                recipes.getDescription(),
                recipes.getIngredients(),
                recipes.getDirections());


    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }


    public void saveToRepository(String name, String description, List<String> ingredients, List<String> directions) {
        repository.save(new Recipes(name, description, ingredients, directions));
    }


}