package com.example.RecipeBook.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<RecipeComponents> components = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<RecipeCookingSteps> cookingSteps = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<UserFavoritesRecipes> favoritesRecipes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<UserLikedRecipes> likedRecipes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<UserRecipes> recipes = new ArrayList<>();

    @Column(name = "recipe_name")
    private String recipeName;
    @Column(name = "cooking_time")
    private Integer cookingTime;
    @Column(name = "for_number_of_people")
    private Byte forNumberOfPeople;
    @Column(name = "number_of_likes")
    private Integer numberOfLikes;
    @Column(name = "number_of_favorites")
    private Integer numberOfFavorites;
    @Column(name = "recipe_description")
    private String recipeDescription;
    @Column(name = "link_on_recipe_photo")
    private String linkOnRecipePhoto;
    @Column(name = "recipe_tags")
    private String recipeTags;
}
