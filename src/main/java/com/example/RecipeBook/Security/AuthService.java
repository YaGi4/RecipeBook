package com.example.RecipeBook.Security;

import com.example.RecipeBook.Exeption.IncorrectPasswordException;
import com.example.RecipeBook.Exeption.UserNotFoundException;
import com.example.RecipeBook.JwtTokens.JwtAuthentication;
import com.example.RecipeBook.JwtTokens.JwtProvider;
import com.example.RecipeBook.JwtTokens.JwtRequest;
import com.example.RecipeBook.JwtTokens.JwtResponse;
import com.example.RecipeBook.entites.User;
import com.example.RecipeBook.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
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

    public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException {
        final User user = userRepository.findByName(authRequest.getLogin())
                .orElseThrow(() -> new UserNotFoundException());
        if (user.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            valueOperations.set(refreshToken, user.getUsername());
            redisTemplate.expire(refreshToken, 30, TimeUnit.DAYS);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new IncorrectPasswordException();
        }
    }

    public JwtResponse refresh(String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String redisLogin = valueOperations.get(refreshToken).toString();
            if (redisLogin != null && redisLogin.equals(login)) {
                final User user = userRepository.findByName(login)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                valueOperations.set(newRefreshToken, user.getUsername());
                redisTemplate.expire(newRefreshToken, 2, TimeUnit.DAYS);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Невалидный JWT токен");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
