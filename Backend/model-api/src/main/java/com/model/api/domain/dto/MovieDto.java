package com.model.api.domain.dto;

import com.model.api.domain.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record MovieDto(

        @Schema(
                description = "Unique movie identifier. Generated automatically.",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        Long id,

        @NotBlank(message = "El título de la película no puede estar vacío.")
        String title,

        @Max(value = 300, message = "La duración de la película no puede ser superior a 300 minutos.")
        Integer duration,

        Genre genre,

        @PastOrPresent
        LocalDate releaseDate,

        @Min(value = 0, message = "El rating no debe ser menor a 0.")
        @Max(value = 5, message = "El rating no debe ser mayor a 5.")
        Double rating,

        Boolean available
) {

}
