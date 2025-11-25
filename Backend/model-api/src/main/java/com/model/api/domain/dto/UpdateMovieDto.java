package com.model.api.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record UpdateMovieDto(

        @PastOrPresent(message = "La fecha de lanzamiento debe ser anterior a la fecha actual.")
        LocalDate releaseDate,

        @Min(value = 0, message = "El rating no puede ser menor que 0")
        @Max(value = 5, message = "El rating no puede ser mayor que 5")
        Double rating,

        Boolean available
) {

}
