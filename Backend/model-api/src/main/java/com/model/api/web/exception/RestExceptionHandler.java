package com.model.api.web.exception;

import com.model.api.domain.exception.MovieAlreadyExistsException;
import com.model.api.domain.exception.MovieDoesNotExistsExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException ex) {
        List<Error> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(new Error(error.getField(), error.getDefaultMessage()));
        });

        return  ResponseEntity.badRequest().body(errors);
    }

    //Exceptions en general:
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception ex) {
        Error error = new Error("unknown-error", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}
