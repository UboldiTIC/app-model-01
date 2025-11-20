package com.model.api.domain.repository;

import com.model.api.domain.dto.MovieDto;
import com.model.api.domain.dto.UpdateMovieDto;

import java.util.List;

public interface MovieRepository {
    List<MovieDto> getAll();
    MovieDto getById(long id);
    MovieDto save(MovieDto movieDto);
    MovieDto update(long id, UpdateMovieDto updateMovieDto);
    boolean deleteById(long id);
}
