package com.model.api.domain.dto;

import com.model.api.domain.Genre;

import java.time.LocalDate;

public record MovieDto(
        String title,
        Integer duration,
        Genre genre,
        LocalDate releaseDate,
        Double rating
) {

}
