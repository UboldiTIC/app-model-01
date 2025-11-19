package com.model.api.domain.dto;

import java.time.LocalDate;

public record UpdateMovieDto(
        //String title,
        LocalDate releaseDate,
        Double rating,
        Boolean available
) {

}
