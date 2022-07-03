package com.example.Recipes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;


@RestController
public class RecipesController {

    @Autowired
    RecipesService recipesService;


    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable int id) {
        return recipesService.getRecipesById(id);
    }

    @PostMapping("/api/recipe/new")
    public Map<String, Long> postRecipes(@AuthenticationPrincipal UserDetails details, @Validated @RequestBody Recipes recipe) {
        recipesService.saveToRepository(recipe.getName(), recipe.getDescription(),
                recipe.getCategory(), LocalDateTime.now(),
                recipe.getIngredients(), recipe.getDirections(), details.getUsername());
        return Map.of("id", recipesService.repository.lastId());
    }


    @GetMapping("/api/recipes")
    public ResponseEntity<?> getRecipes() {
        return new ResponseEntity<>(recipesService.getAllRecipes(), HttpStatus.OK);
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<?> deleteRecipes(@AuthenticationPrincipal UserDetails details, @PathVariable long id) {
        if (recipesService.getRecipesById(id).getStatusCode() == HttpStatus.OK) {
            Recipes recipes = recipesService.repository.findById(id).get();
            if (recipes.getEmail().equals(details.getUsername())) {
                recipesService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<?> putRecipes(@AuthenticationPrincipal UserDetails details, @PathVariable long id, @Validated @RequestBody Recipes recipe) {
        try {
            Recipes recipes = recipesService.repository.findById(id).get();
            if (recipes.getEmail().equals(details.getUsername())) {
                recipes.setName(recipe.getName());
                recipes.setCategory(recipe.getCategory());
                recipes.setDate(LocalDateTime.now());
                recipes.setDescription(recipe.getDescription());
                recipes.setIngredients(recipe.getIngredients());
                recipes.setDirections(recipe.getDirections());
                recipesService.repository.save(recipes);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
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
        } else if (category == null & name != null) {
            System.out.println(recipesService.getRepository().findByNameContainingIgnoreCase(name, null));
            return new ResponseEntity<>(recipesService.getRepository().findByNameContainingIgnoreCase(name, null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/public")
    public String testPublic() {
        return "/public is accessed";
    }

    @GetMapping("/authenticated")
    public String testAuth() {
        return "/authenticated is accessed";
    }

    @GetMapping("/user")
    public String testUser() {
        return "/user is accessed";
    }

    @GetMapping("/admin")
    public String testAdmin() {
        return "/admin is accessed";
    }

    @GetMapping("/test")
    public String test() {
        return "/test is accessed";
    }

    @GetMapping("/username")
    public void username(Authentication auth) {
        System.out.println(auth.getName());
    }

    @GetMapping("/details")
    public void details(Authentication auth) {
        UserDetails details = (UserDetails) auth.getPrincipal();

        System.out.println("Username: " + details.getUsername());
        System.out.println("User has authorities/roles: " + details.getAuthorities());
        System.out.println("Username: " + details.getUsername());

    }

    @GetMapping("/username2")
    public void username2(@AuthenticationPrincipal UserDetails details) {
        System.out.println(details.getUsername());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        GrantedAuthority grantedAuthority = () -> "ROLE_ADMIN";
        System.out.println(details.getAuthorities().contains(grantedAuthority));

    }

}
