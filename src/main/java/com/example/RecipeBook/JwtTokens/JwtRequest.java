package com.example.RecipeBook.JwtTokens;


import lombok.Data;

@Data
public class JwtRequest {

    private String login;
    private String password;

}
