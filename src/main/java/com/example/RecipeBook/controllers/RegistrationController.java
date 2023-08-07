package com.example.RecipeBook.controllers;

import com.example.RecipeBook.Dtos.UserRequestDto;
import com.example.RecipeBook.Dtos.UserResponseDto;
import com.example.RecipeBook.Exeption.UserAlreadyExists;
import com.example.RecipeBook.services.AddNewUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RegistrationController {
    private final AddNewUser addNewUser;

    @PostMapping("registration")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto) throws UserAlreadyExists {
        UserResponseDto userResponseDto = addNewUser.addUser(userRequestDto);
            return ResponseEntity.ok(userResponseDto);
    }
}
