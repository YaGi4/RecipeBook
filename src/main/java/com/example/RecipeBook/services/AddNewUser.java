package com.example.RecipeBook.services;

import com.example.RecipeBook.Dtos.UserRequestDto;
import com.example.RecipeBook.Dtos.UserResponseDto;
import com.example.RecipeBook.Exeption.UserAlreadyExists;
import com.example.RecipeBook.entites.User;
import com.example.RecipeBook.repository.UserRepository;
import com.example.RecipeBook.Mappers.UserEntityResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddNewUser {
    private final UserRepository userRepository;
    private final UserEntityResponseMapper userEntityResponseMapper;

    public UserResponseDto addUser(UserRequestDto userRequestDto) throws UserAlreadyExists {
        if (userRepository.findByLogin(userRequestDto.getLogin()).isPresent()) {
            throw new UserAlreadyExists("User already exists");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(userRequestDto.getPassword());
        userRepository.addUser(userRequestDto.getName(), userRequestDto.getLogin(), password);
        User user = userRepository.findByLogin(userRequestDto.getLogin()).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return userEntityResponseMapper.modelToDto(user);

    }
}
