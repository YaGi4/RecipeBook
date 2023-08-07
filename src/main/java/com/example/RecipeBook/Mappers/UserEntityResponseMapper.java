package com.example.RecipeBook.Mappers;

import com.example.RecipeBook.Dtos.UserResponseDto;
import com.example.RecipeBook.entites.User;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface UserEntityResponseMapper {
    UserResponseDto modelToDto (User user);
}
