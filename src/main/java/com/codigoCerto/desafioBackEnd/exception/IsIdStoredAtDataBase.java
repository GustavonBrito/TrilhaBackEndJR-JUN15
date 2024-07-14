package com.codigoCerto.desafioBackEnd.exception;

public class IsIdStoredAtDataBase extends RuntimeException{
    public IsIdStoredAtDataBase(String message) {
        super(message);
    }
}
