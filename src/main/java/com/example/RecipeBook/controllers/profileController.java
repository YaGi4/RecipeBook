package com.example.RecipeBook.controllers;

import com.example.RecipeBook.Dtos.ProfileDto;
import com.example.RecipeBook.services.GetAboutUser;
import com.example.RecipeBook.services.TokenService;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class profileController {
    private final TokenService tokenService;
    private final GetAboutUser getAboutUser;

    @GetMapping("/userProfile")
    public ProfileDto getProfile(ServletRequest request){
        Long userId = tokenService.getUserIdFromRequestToken(request);
        return getAboutUser.getInformation(userId);
    }
}
