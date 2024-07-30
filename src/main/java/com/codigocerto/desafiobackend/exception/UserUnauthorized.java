package com.codigocerto.desafiobackend.exception;

public class UserUnauthorized extends RuntimeException{
    public UserUnauthorized(String message) {
        super(message);
    }
}
