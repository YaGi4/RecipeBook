package com.example.RecipeBook.entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long ID;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserFavoritesRecipes> userFavoritesRecipes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserLikedRecipes> userLikedRecipes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserRecipes> userRecipes = new ArrayList<>();

    private String user_name;
    private String email;
    private String password;
    private String about_user;
}

