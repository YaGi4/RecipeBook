package com.example.RecipeBook.services;

import com.example.RecipeBook.entites.User;
import com.example.RecipeBook.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getByLogin(@NonNull String login) {
        return userRepository.findByName(login);
    }
}
