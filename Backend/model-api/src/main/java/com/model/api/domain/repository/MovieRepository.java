package com.model.api.domain.repository;

import com.model.api.domain.dto.MovieDto;

import java.util.List;

public interface MovieRepository {
    List<MovieDto> getAll();
}
