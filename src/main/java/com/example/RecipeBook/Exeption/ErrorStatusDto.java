package com.example.RecipeBook.Exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorStatusDto {
    private HttpStatus statusCode;
    private Integer errorCode;
    private String message;

}
