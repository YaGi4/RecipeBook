package com.example.RecipeBook.Dtos;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String login;
    private String password;
    private String description;
}
