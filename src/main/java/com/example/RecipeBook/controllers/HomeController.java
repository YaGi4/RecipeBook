package com.example.RecipeBook.controllers;

import com.example.RecipeBook.entites.User;
import com.example.RecipeBook.JwtTokens.JwtProvider;
import com.example.RecipeBook.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @GetMapping
    public String home(Model model){
        User user = userRepository.findByID(1L);
        //List<RecipeCookingSteps> recipeCookingStepsList = recipeCookingStepsRepository.findByRecipeId(recipeList.get(0).getId());
        String content = jwtProvider.generateAccessToken(user);
        content += "\n" + jwtProvider.generateRefreshToken(user);
        model.addAttribute("content",content);
        model.addAttribute("title", "Главная страница");
        model.addAttribute("bold", ";font-weight: bold");
        return "home";
    }
    @RequestMapping("/recipes")
    public String recipes()
    {
        return "recipes";
    }
}
