package com.example.Recipes;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class Users {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Email
    @Pattern(regexp = ".*[@]+.*[.]+.*")
    private String email;

    @Column
    @NotNull
    @NotBlank
    @Size(min = 8)
    private String password;

    public Users(String email, String password) {
        this.email = email;
        this.password = password;

    }

    public String getEmail() {
        return email;
    }

    public Users() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
