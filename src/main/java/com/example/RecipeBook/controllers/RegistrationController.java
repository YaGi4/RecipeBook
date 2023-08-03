package com.example.RecipeBook.controllers;

import com.example.RecipeBook.Dtos.UserRequestDto;
import com.example.RecipeBook.Dtos.UserResponseDto;
import com.example.RecipeBook.services.AddNewUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RegistrationController {
    private final AddNewUser addNewUser;

    @PostMapping("registration")
    public UserResponseDto addUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = addNewUser.addUser(userRequestDto);
            return userResponseDto;
    }
}
