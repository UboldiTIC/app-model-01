package com.model.api.domain.service;

import com.model.api.domain.dto.MovieDto;
import com.model.api.domain.dto.UpdateMovieDto;
import com.model.api.domain.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDto> getAll() {
        return this.movieRepository.getAll();
    }

    public MovieDto getById(long id) {
        return this.movieRepository.getById(id);
    }

    // Post Method:
    public MovieDto add(MovieDto movieDto) {
        return this.movieRepository.save(movieDto);
    }

    // Put Method:
    public MovieDto update(long id, UpdateMovieDto updateMovieDto) {
        return this.movieRepository.update(id, updateMovieDto);
    }

    // Delete Method:
    public boolean deleteById(long id) {
        return this.movieRepository.deleteById(id);
    }
}
