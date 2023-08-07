package com.example.RecipeBook.controllers;

import com.example.RecipeBook.Dtos.RecipeDto;
import com.example.RecipeBook.Dtos.RecipeExtendedDto;
import com.example.RecipeBook.Dtos.UserRequest;
import com.example.RecipeBook.services.GetRecipe;
import com.example.RecipeBook.services.TokenService;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RecipesController {
    private final GetRecipe getRecipe;
    private final TokenService tokenService;

    @GetMapping("/favoriteRecipes")
    public List<RecipeExtendedDto> favoriteRecipes(ServletRequest request){
        Long userId = tokenService.getUserIdFromRequestToken(request);
        List<RecipeExtendedDto> recipesExtendedDto = getRecipe.getUserFavoriteRecipes(userId);
        return ResponseEntity.ok(recipesExtendedDto).getBody();
    }

    @GetMapping("/userRecipes")
    public List<RecipeExtendedDto> userRecipes(ServletRequest request){
        Long userId = tokenService.getUserIdFromRequestToken(request);
        List<RecipeExtendedDto> recipesExtendedDto = getRecipe.getUserRecipes(userId);
        return ResponseEntity.ok(recipesExtendedDto).getBody();
    }

}
