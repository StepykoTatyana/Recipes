package com.example.Recipes.JPATask.ValidateTask;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;
import java.util.*;
import java.util.concurrent.*;

@RestController
public class Controller {
    final List<Task> tempDb = new CopyOnWriteArrayList<>();
    final List<Colors> colorsList = new CopyOnWriteArrayList<>();

    @PostMapping("/api/colors")
    public void addColor(@Validated @RequestBody Colors colors) {
        colorsList.add(colors);
    }

    @PostMapping("/tasks")
    public void addTask(@Validated @RequestBody Task task) {
        tempDb.add(task);
    }
}

class Task {
    @NotBlank
    @Size(min = 1, max = 50)
    String name;

    @NotBlank
    @Size(min = 1, max = 200)
    String description;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Task() {
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
}

class Colors {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 12)
    private List<String> colors;

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }
}