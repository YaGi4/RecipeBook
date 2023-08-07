package com.example.RecipeBook.services;

import com.example.RecipeBook.Dtos.ProfileDto;
import com.example.RecipeBook.Dtos.RecipeDto;
import com.example.RecipeBook.Dtos.RecipeExtendedDto;
import com.example.RecipeBook.Dtos.UserRequest;
import com.example.RecipeBook.Mappers.RecipeEntityDtoMapper;
import com.example.RecipeBook.Mappers.UserEntityResponseMapper;
import com.example.RecipeBook.entites.User;
import com.example.RecipeBook.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class GetAboutUser {

    private final UserRepository userRepository;
    private final UserRecipesRepository userRecipesRepository;
    private final UserLikedRecipesRepository userLikedRecipesRepository;
    private final UserFavoritesRecipesRepository userFavoritesRecipesRepository;
    private final RecipeRepository recipeRepository;
    private final UserEntityResponseMapper userEntityResponseMapper;
    private final RecipeEntityDtoMapper recipeEntityDtoMapper;

    public ProfileDto getInformation(Long userId){
        ProfileDto profileDto = new ProfileDto();
        profileDto.setUserResponseDto(userEntityResponseMapper.modelToDto(userRepository.findByID(userId)));
        List<Long> userRecipes = userRecipesRepository.findByUserId(userId);
        List<RecipeExtendedDto> recipeExtendedDtoList = new ArrayList<>();
        profileDto.setTotalRecipes(userRecipesRepository.findByUserId(userId).size());

        Integer userLikes = 0;
        Integer userFavorite = 0;
        for(Long id: userRecipes){
            userLikes += userLikedRecipesRepository.findIdByRecipeId(id).size();
            userFavorite += userFavoritesRecipesRepository.findIdByRecipeId(id).size();
            RecipeExtendedDto recipeExtendedDto = new RecipeExtendedDto();
            recipeExtendedDto.setRecipeDto(recipeEntityDtoMapper.modelToDto(recipeRepository.findByID(id)));
            recipeExtendedDto.setFavorites(userFavoritesRecipesRepository.findIdByRecipeId(id).size());
            recipeExtendedDto.setIsFavoriteByUser(userFavoritesRecipesRepository.findByUserId(userId).contains(id));
            recipeExtendedDto.setLikes(userLikedRecipesRepository.findIdByRecipeId(id).size());
            recipeExtendedDto.setIsLikedByUser(userLikedRecipesRepository.findRecipeByUserId(userId).contains(id));
            recipeExtendedDtoList.add(recipeExtendedDto);
        }
        profileDto.setTotalLikes(userLikes);
        profileDto.setTotalFavorites(userFavorite);
        profileDto.setRecipeExtendedDto(recipeExtendedDtoList);
        return profileDto;
    }
}
