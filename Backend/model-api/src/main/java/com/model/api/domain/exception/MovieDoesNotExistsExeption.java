package com.model.api.domain.exception;

public class MovieDoesNotExistsExeption extends RuntimeException{
    public MovieDoesNotExistsExeption(Long movieId) {
        super("No existe ninguna película con el id Nº: " + movieId);
    }
}
