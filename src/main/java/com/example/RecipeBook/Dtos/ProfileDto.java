package com.example.RecipeBook.Dtos;

import lombok.Data;

import java.util.List;

@Data
public class ProfileDto {
    private UserResponseDto userResponseDto;
    private Integer totalRecipes;
    private Integer totalLikes;
    private Integer totalFavorites;
    private List<RecipeExtendedDto> recipeExtendedDto;
}
