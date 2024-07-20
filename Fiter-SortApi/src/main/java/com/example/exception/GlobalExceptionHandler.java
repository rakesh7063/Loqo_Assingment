package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Exception thrown when no products match the filter criteria.
    @ExceptionHandler(NoProductsFound.class)
    public ResponseEntity<MyErrorDetails> NoProductsFoundExceptionHandler(NoProductsFound productsFound, WebRequest req){
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), productsFound.getMessage(), req.getDescription(false));
        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }
}
