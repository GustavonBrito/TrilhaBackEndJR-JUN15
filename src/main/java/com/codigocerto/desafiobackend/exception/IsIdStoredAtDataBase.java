package com.codigocerto.desafiobackend.exception;

public class IsIdStoredAtDataBase extends RuntimeException{
    public IsIdStoredAtDataBase(String message) {
        super(message);
    }
}
