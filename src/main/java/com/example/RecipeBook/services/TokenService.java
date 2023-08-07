package com.example.RecipeBook.services;

import com.example.RecipeBook.JwtTokens.JwtProvider;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class TokenService {
    private final JwtProvider jwtProvider;
    public Long getUserIdFromRequestToken(ServletRequest request) {
        final String token = getTokenFromRequest((HttpServletRequest) request);
        final Claims claims = jwtProvider.getAccessClaims(token);
        Long id = claims.get("id", Long.class);
        return id;
    }
    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader("Authorization");
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
