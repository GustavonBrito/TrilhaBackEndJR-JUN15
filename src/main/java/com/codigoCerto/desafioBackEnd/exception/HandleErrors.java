package com.codigoCerto.desafioBackEnd.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice()
public class HandleErrors {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String, String> fieldErrorsMap = fieldErrors.stream()
                .collect(Collectors.toMap(FieldError::getField,
                        error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Field error"));
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));

        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, "Check the field(s) error(s): " + fields );

        problemDetail.setType(URI.create("https://example.net/errors/bad-request-exception"));
        problemDetail.setTitle("Bad Request Exception, invalid fields");
        problemDetail.setProperty("timeStamp", LocalDateTime.now());
        problemDetail.setProperty("errors", fieldErrorsMap);

        return problemDetail;
    }

    @ExceptionHandler(EmailAlreadyExists.class)
    public ProblemDetail handleUserWhoExists(EmailAlreadyExists error, HttpServletRequest request){
        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, error.getMessage());
        problemDetail.setTitle("Email cadastrado");
        problemDetail.setProperty("timeStamp", LocalDateTime.now());
        problemDetail.setType(URI.create("errors/user-already-exists"));
        return problemDetail;
    }

    @ExceptionHandler(IsIdStoredAtDataBase.class)
    public ProblemDetail handleIdWhoDoesntExists (IsIdStoredAtDataBase error, HttpServletRequest request){
        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND, error.getMessage());
        problemDetail.setTitle("Id não existe no sistema");
        problemDetail.setProperty("timeStamp", LocalDateTime.now());
        problemDetail.setType(URI.create("errors/id-is-not-registered"));
        return problemDetail;
    }

    @ExceptionHandler(IsPasswordEquals.class)
    public ProblemDetail handleDifferentPasswords (IsPasswordEquals error, HttpServletRequest request){
        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, error.getMessage());
        problemDetail.setTitle("Senhas não são iguais");
        problemDetail.setProperty("timeStamp", LocalDateTime.now());
        problemDetail.setType(URI.create("errors/different-passwords"));
        return problemDetail;
    }

    @ExceptionHandler(UserUnauthorized.class)
    public ProblemDetail handleUserUnauthorized (UserUnauthorized error, HttpServletRequest request){
        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.UNAUTHORIZED, error.getMessage());
        problemDetail.setTitle("Usuario não autorizado");
        problemDetail.setProperty("timeStamp", LocalDateTime.now());
        problemDetail.setType(URI.create("errors/user-unauthorized"));
        return problemDetail;
    }
}
