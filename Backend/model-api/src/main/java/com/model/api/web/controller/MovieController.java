package com.model.api.web.controller;

import com.model.api.domain.dto.MovieDto;
import com.model.api.domain.dto.SuggestRequestDto;
import com.model.api.domain.dto.UpdateMovieDto;
import com.model.api.domain.service.ModelAIService;
import com.model.api.domain.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movies", description = " Operations about movies.") // Swagger title.
public class MovieController {
    private final MovieService movieService;
    private final ModelAIService modelAIService;

    public MovieController(MovieService movieService, ModelAIService modelAIService) {
        this.movieService = movieService;
        this.modelAIService = modelAIService;
    }

    // Get Method:
    @GetMapping
    @Operation(
            summary = "Get all movies.",
            description = "Get all the movies that exist in the database."
    )
    public ResponseEntity<List<MovieDto>> getAll() {
        return ResponseEntity.ok(this.movieService.getAll());
    }

    //Aplicación de ResponseEntity para recuperar y devolver códigos Http:
    @GetMapping("/{id}")
    @Operation(
            summary = "Get a movie by id.",
            description = "Returns the movie that matches the provided identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Movie found."),
                    @ApiResponse(responseCode = "404", description = "Movie not found.", content = @Content)
            }
    ) // Swagger
    public ResponseEntity<MovieDto> getById(@Parameter(description = "Identifier of the movie to retrieve.", example = "9") @PathVariable long id){

        //ResponseEntity - Todos los servicios lo deben implementar.
        MovieDto movieDto = this.movieService.getById(id);

        if (movieDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieDto);
    }

    // Post (Create) Method:
    @PostMapping
    @Operation(
            summary = "Creates a new movie.",
            description = "Creates a new movie using the data provided in the request body."
    )
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "201",
                description = "Product created successfully.",
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = MovieDto.class)
                )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request. Body validation failed.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(example = "{\"status\": 400, \"error\": \"The name is required.\"}")
                    )

            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict. A product with the same name already exists.",
                    content = @Content
            )
    })
    public ResponseEntity<MovieDto> add(@RequestBody @Valid MovieDto movieDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.movieService.add(movieDto));
    }

    // Recomendación con IA:
    @PostMapping("/suggest")
    @Operation(
            summary = "Generate suggested movies.",
            description = "Generate a list of suggested movies based on the customer's preferences and include a short description for each one."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Here is your list of suggested movies."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No movies have been suggested."
                    )
            }
    )
    public ResponseEntity<String> generateMoviesSuggestion(@RequestBody @Valid SuggestRequestDto suggestRequestDto) {

        return ResponseEntity.ok(this.modelAIService.generateMoviesSuggestion(suggestRequestDto.userPreferences()));

    }

    // Put (update) Method:
    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing movie in the database.",
            description = "By sending the ID, we can edit certain movie fields such as release date, availability, and rating."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The movie was successfully updated."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The movie was not updated; please try again."
                    )
            }
    )
    public ResponseEntity<MovieDto> update(@Parameter(description = "Identifier of the movie to update.", example = "9") @PathVariable long id, @RequestBody @Valid UpdateMovieDto updateMovieDto) {
        return ResponseEntity.ok(this.movieService.update(id, updateMovieDto));
    }

    // Delete Method:
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete movie by id.",
            description = "Sending the ID deletes the movie from the database."
    )
    public ResponseEntity<Void> deleteById(@Parameter(description = "Identifier of the movie to delete.", example = "9") @PathVariable long id) {
        boolean deleted = movieService.deleteById(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
