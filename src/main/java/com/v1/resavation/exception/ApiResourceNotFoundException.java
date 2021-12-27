package com.v1.resavation.exception;

public class ApiResourceNotFoundException extends RuntimeException{
    public ApiResourceNotFoundException(String message) {
        super(message);
    }
}
