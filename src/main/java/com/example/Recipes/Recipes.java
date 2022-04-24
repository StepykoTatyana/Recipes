package com.example.Recipes;

import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Recipes {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NotNull
    @NotEmpty
    @NotBlank
    private String name;
    @Column
    @NotNull
    @NotEmpty
    @NotBlank
    private String category;


    @Column
    @DateTimeFormat
    private LocalDateTime date;

    @Column
    @NotNull
    @NotEmpty
    @NotBlank
    private String description;


    @NotNull
    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "INGREDIENTS", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "ingredients")
    @Size(min = 1)
    @OrderColumn
    private List<String> ingredients;
    @NotNull
    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "DIRECTIONS", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "directions")
    @Size(min = 1)
    @OrderColumn
    private List<String> directions;


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getDirections() {
        return directions;
    }

    public void setDirections(List<String> directions) {
        this.directions = directions;
    }

    public Recipes() {
    }

    public Recipes(String name, String description, String category, LocalDateTime date, List<String> ingredients, List<String> directions) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.date = date;
        this.ingredients = ingredients;
        this.directions = directions;
    }
}


