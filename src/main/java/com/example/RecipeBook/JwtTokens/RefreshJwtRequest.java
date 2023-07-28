package com.example.RecipeBook.JwtTokens;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class RefreshJwtRequest {
    @NotNull
    public String refreshToken;
}
