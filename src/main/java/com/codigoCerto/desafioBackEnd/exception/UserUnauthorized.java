package com.codigoCerto.desafioBackEnd.exception;

public class UserUnauthorized extends RuntimeException{
    public UserUnauthorized(String message) {
        super(message);
    }
}
