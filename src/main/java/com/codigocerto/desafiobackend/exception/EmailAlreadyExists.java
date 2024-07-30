package com.codigocerto.desafiobackend.exception;

public class EmailAlreadyExists extends RuntimeException{
    public EmailAlreadyExists(String message) {
        super(message);
    }
}
