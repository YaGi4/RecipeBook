package com.example.RecipeBook.Dtos;

import lombok.Data;
@Data
public class RecipeExtendedDto {
    private RecipeDto recipeDto;
    private Integer likes;
    private Boolean isLikedByUser;
    private Integer favorites;
    private Boolean isFavoriteByUser;
}
