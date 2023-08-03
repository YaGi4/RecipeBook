package com.example.RecipeBook.Dtos;

import lombok.Data;

@Data
public class UserRequestDto {
    private String name;
    private String login;
    private String password;
}
