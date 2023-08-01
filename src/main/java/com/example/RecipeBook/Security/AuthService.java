package com.example.RecipeBook.Security;

import com.example.RecipeBook.Exeption.NotValidTokenException;
import com.example.RecipeBook.Exeption.UserNotFoundException;
import com.example.RecipeBook.Exeption.WrongPasswordException;
import com.example.RecipeBook.JwtTokens.JwtAuthentication;
import com.example.RecipeBook.JwtTokens.JwtProvider;
import com.example.RecipeBook.JwtTokens.JwtRequest;
import com.example.RecipeBook.JwtTokens.JwtResponse;
import com.example.RecipeBook.entites.User;
import com.example.RecipeBook.repository.UserRepository;
import com.example.RecipeBook.services.PasswordService;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AuthService {
    private ValueOperations valueOperations;
    private RedisTemplate redisTemplate;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public AuthService(RedisTemplate redisTemplate, UserRepository userRepository, JwtProvider jwtProvider) {
        this.valueOperations = redisTemplate.opsForValue();
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.redisTemplate = redisTemplate;
    }

    public JwtResponse login(@NonNull JwtRequest authRequest) throws UserNotFoundException, WrongPasswordException {
        final User user = userRepository.findByName(authRequest.getLogin())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (PasswordService.getPasswordEncoder().matches(authRequest.getPassword(), user.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            valueOperations.set(refreshToken, user.getUsername());
            redisTemplate.expire(refreshToken, 30, TimeUnit.DAYS);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new WrongPasswordException("wrong password");
        }
    }

    public JwtResponse refresh(String refreshToken) throws NotValidTokenException, UserNotFoundException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String redisLogin = valueOperations.get(refreshToken).toString();
            if (redisLogin != null && redisLogin.equals(login)) {
                final User user = userRepository.findByName(login)
                        .orElseThrow(() -> new UserNotFoundException("User not found"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                valueOperations.set(newRefreshToken, user.getUsername());
                redisTemplate.expire(newRefreshToken, 2, TimeUnit.DAYS);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new NotValidTokenException("Not a valid token");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
