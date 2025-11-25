package com.model.api.web.exception;

import com.model.api.domain.exception.MovieAlreadyExistsException;
import com.model.api.domain.exception.MovieDoesNotExistsExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Esta clase acptura las excepciones que ocurren dentro de la API
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ResponseEntity<Error> handleException(MovieAlreadyExistsException ex) {
        Error error = new Error("movie-already-exists", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MovieDoesNotExistsExeption.class)
    public String handleException(MovieDoesNotExistsExeption ex) {
        return ex.getMessage();
    }
}
