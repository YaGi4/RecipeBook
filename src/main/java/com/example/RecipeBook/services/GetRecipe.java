package com.example.RecipeBook.services;


import com.example.RecipeBook.Dtos.RecipeDto;
import com.example.RecipeBook.Dtos.RecipeExtendedDto;
import com.example.RecipeBook.Dtos.UserRequest;
import com.example.RecipeBook.Mappers.RecipeEntityDtoMapper;
import com.example.RecipeBook.repository.RecipeRepository;
import com.example.RecipeBook.repository.UserFavoritesRecipesRepository;
import com.example.RecipeBook.repository.UserLikedRecipesRepository;
import com.example.RecipeBook.repository.UserRecipesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetRecipe {
    private final UserFavoritesRecipesRepository userFavoritesRecipesRepository;
    private final UserLikedRecipesRepository userLikedRecipesRepository;
    private final UserRecipesRepository userRecipesRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeEntityDtoMapper recipeEntityDtoMapper;

    public List<RecipeExtendedDto> getUserFavoriteRecipes(Long userId)
    {
        List<Long> recipeIds = userFavoritesRecipesRepository.findByUserId(userId);
        List<RecipeExtendedDto> recipeExtendedDtoList = new ArrayList<>();
        for(Long id: recipeIds){
            RecipeExtendedDto recipeExtendedDto = new RecipeExtendedDto();
            recipeExtendedDto.setRecipeDto(recipeEntityDtoMapper.modelToDto(recipeRepository.findByID(id)));
            recipeExtendedDto.setFavorites(userFavoritesRecipesRepository.findIdByRecipeId(id).size());
            recipeExtendedDto.setIsFavoriteByUser(userFavoritesRecipesRepository.findByUserId(userId).isEmpty());
            recipeExtendedDto.setFavorites(userLikedRecipesRepository.findIdByRecipeId(id).size());
            recipeExtendedDto.setIsFavoriteByUser(userLikedRecipesRepository.findRecipeByUserId(userId).isEmpty());
            recipeExtendedDtoList.add(recipeExtendedDto);
        }
        return  recipeExtendedDtoList;
    }

    public List<RecipeExtendedDto> getUserRecipes(Long userId)
    {
        List<Long> recipeIds = userRecipesRepository.findByUserId(userId);
        List<RecipeExtendedDto> recipeExtendedDtoList = new ArrayList<>();
        for(Long id: recipeIds){
            RecipeExtendedDto recipeExtendedDto = new RecipeExtendedDto();
            recipeExtendedDto.setRecipeDto(recipeEntityDtoMapper.modelToDto(recipeRepository.findByID(id)));
            recipeExtendedDto.setFavorites(userFavoritesRecipesRepository.findIdByRecipeId(id).size());
            recipeExtendedDto.setIsFavoriteByUser(userFavoritesRecipesRepository.findByUserId(userId).isEmpty());
            recipeExtendedDto.setFavorites(userLikedRecipesRepository.findIdByRecipeId(id).size());
            recipeExtendedDto.setIsFavoriteByUser(userLikedRecipesRepository.findRecipeByUserId(userId).isEmpty());
            recipeExtendedDtoList.add(recipeExtendedDto);
        }
        return  recipeExtendedDtoList;
    }
}
