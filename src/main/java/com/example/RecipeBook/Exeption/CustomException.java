package com.example.RecipeBook.Exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ControllerAdvice
public class CustomException {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorStatusDto userNotFound(UserNotFoundException message){
            ErrorStatusDto errorStatusDto = new ErrorStatusDto(HttpStatus.NOT_FOUND, 1001, message.getMessage());
            return errorStatusDto;
    }
    @ExceptionHandler(WrongPasswordException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorStatusDto wrongPassword(WrongPasswordException message){
            Date date = new Date();
            ErrorStatusDto errorStatusDto = new ErrorStatusDto(HttpStatus.NOT_FOUND, 1002, message.getMessage());
            return errorStatusDto;

    }
    @ExceptionHandler(NotValidTokenException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorStatusDto wrongPassword(NotValidTokenException message){
        Date date = new Date();
        ErrorStatusDto errorStatusDto = new ErrorStatusDto(HttpStatus.BAD_REQUEST, 1003, message.getMessage());
        return errorStatusDto;

    }
}
