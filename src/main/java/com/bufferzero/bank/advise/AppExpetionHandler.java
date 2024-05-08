package com.bufferzero.bank.advise;

import com.bufferzero.bank.exceptions.AccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExpetionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgs(MethodArgumentNotValidException ex){
        Map<String, String> errMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(err -> {
            errMap.put(err.getField(), err.getDefaultMessage());
        });
        return errMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleAccountNotFoundExp(Exception ex){
        Map<String, String> errMap = new HashMap<>();
        errMap.put("error", ex.getMessage());
        return errMap;
    }
}
