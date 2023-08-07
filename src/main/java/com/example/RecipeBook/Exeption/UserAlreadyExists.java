package com.example.RecipeBook.Exeption;

public class UserAlreadyExists extends Exception{
    public UserAlreadyExists(String message){
        super(message);
    }
}
