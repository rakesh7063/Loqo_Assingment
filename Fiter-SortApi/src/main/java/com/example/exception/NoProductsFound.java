package com.example.exception;

public class NoProductsFound extends Exception{
    public NoProductsFound(String message) {
        super(message);
    }
}
