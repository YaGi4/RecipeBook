package com.example.RecipeBook.Dtos;

import lombok.Data;

@Data
public class RecipeDto {
    private Long id;
    private String recipeName;
    private Integer cookingTime;
    private Byte forNumberOfPeople;
    private Integer numberOfLikes;
    private Integer numberOfFavorites;
    private String recipeDescription;
    private String linkOnRecipePhoto;
    private String recipeTags;
}
