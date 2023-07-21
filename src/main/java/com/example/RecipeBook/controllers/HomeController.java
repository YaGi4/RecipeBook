package com.example.RecipeBook.controllers;

import com.example.RecipeBook.entites.User;
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

    @GetMapping
    public String home(Model model){
        List<User> user = userRepository.findAllFromUserRepository();
        //List<RecipeCookingSteps> recipeCookingStepsList = recipeCookingStepsRepository.findByRecipeId(recipeList.get(0).getId());
        String content = user.toString();
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
