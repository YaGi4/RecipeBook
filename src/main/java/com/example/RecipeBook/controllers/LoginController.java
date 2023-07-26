package com.example.RecipeBook.controllers;

import com.example.RecipeBook.JwtTokens.JwtRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
    @GetMapping
    public String Login() {
        return "login";
    }
    @PostMapping
    public void UserLogin(@RequestBody JwtRequest jwtRequest) {
        String content = jwtRequest.getLogin();
    }
}
