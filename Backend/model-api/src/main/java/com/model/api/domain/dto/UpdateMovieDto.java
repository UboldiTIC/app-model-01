package com.model.api.domain.dto;

import java.time.LocalDate;

public record UpdateMovieDto(
        LocalDate releaseDate,
        Double rating,
        Boolean available
) {

}
