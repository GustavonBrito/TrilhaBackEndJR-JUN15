package com.codigoCerto.desafioBackEnd.exception;

public class IsPasswordEquals extends RuntimeException{
    public IsPasswordEquals(String message) {
        super(message);
    }
}
