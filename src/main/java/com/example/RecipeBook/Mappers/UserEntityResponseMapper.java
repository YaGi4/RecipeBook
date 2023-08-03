package com.example.RecipeBook.Mappers;

import com.example.RecipeBook.Dtos.UserRequestDto;
import com.example.RecipeBook.Dtos.UserResponseDto;
import com.example.RecipeBook.entites.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserEntityResponseMapper {
    UserEntityResponseMapper MAPPER = Mappers.getMapper(UserEntityResponseMapper.class);
    UserResponseDto toDto (Optional<User> user);
}
