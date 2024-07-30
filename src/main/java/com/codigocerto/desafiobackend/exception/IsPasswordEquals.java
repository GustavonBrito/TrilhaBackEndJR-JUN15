package com.codigocerto.desafiobackend.exception;

public class IsPasswordEquals extends RuntimeException{
    public IsPasswordEquals(String message) {
        super(message);
    }
}
