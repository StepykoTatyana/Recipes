package com.example.Recipes.JPATask.ValidateTask;

import javax.validation.constraints.*;
import java.util.*;

public class SpecialAgent {
    @NotNull
    private String name;


    @NotEmpty
    private String surname;


    @NotBlank
    @Size(min = 1, max = 3)
    @Pattern(regexp = "[0-9]{1,3}")
    private String code;

    private String status;


    @Min(value = 18, message = "Age must be greater than or equal to 18")
    private int age;


    @Size(min = 0, max = 4)
    private List<String> cars;


    @Max(5)
    private int numberOfCurrentMissions;


    @NotNull
    @Email
    private String email;

    public String getName() {
        return name;
    }

    public List<String> getCars() {
        return cars;
    }

    public void setCars(List<String> cars) {
        this.cars = cars;
    }

    public int getNumberOfCurrentMissions() {
        return numberOfCurrentMissions;
    }

    public void setNumberOfCurrentMissions(int numberOfCurrentMissions) {
        this.numberOfCurrentMissions = numberOfCurrentMissions;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
// getters and setters
}