package com.example.RecipeBook.controllers;

import com.example.RecipeBook.Exeption.NotValidTokenException;
import com.example.RecipeBook.Exeption.UserNotFoundException;
import com.example.RecipeBook.Exeption.WrongPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.RecipeBook.Security.AuthService;
import com.example.RecipeBook.JwtTokens.JwtResponse;
import com.example.RecipeBook.JwtTokens.JwtRequest;
import com.example.RecipeBook.JwtTokens.RefreshJwtRequest;


@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) throws UserNotFoundException, WrongPasswordException {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

/*
    @PostMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) throws AuthException, CustomExeption {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
*/

    @PostMapping("refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) throws NotValidTokenException, UserNotFoundException {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
}
