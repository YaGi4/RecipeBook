package com.example.RecipeBook.Exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Incorrect password")
public class IncorrectPasswordException extends RuntimeException{
}
