package com.model.api.web.controller;

import com.model.api.domain.dto.MovieDto;
import com.model.api.domain.dto.SuggestRequestDto;
import com.model.api.domain.dto.UpdateMovieDto;
import com.model.api.domain.service.ModelAIService;
import com.model.api.domain.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ModelAIService modelAIService;

    public MovieController(MovieService movieService, ModelAIService modelAIService) {
        this.movieService = movieService;
        this.modelAIService = modelAIService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAll() {
        return ResponseEntity.ok(this.movieService.getAll());
    }

    //Aplicación de ResponseEntity para recuperar y devolver códigos Http:
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getById(@PathVariable long id){

        //ResponseEntity - Todos los servicios lo deben implementar.
        MovieDto movieDto = this.movieService.getById(id);

        if (movieDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieDto);
    }

    // Post (Create) Method:
    @PostMapping
    public ResponseEntity<MovieDto> add(@RequestBody MovieDto movieDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.movieService.add(movieDto));
    }

    //Recomendación con IA:
    @PostMapping("/suggest")
    public ResponseEntity<String> generateMoviesSuggestion(@RequestBody SuggestRequestDto suggestRequestDto) {

        return ResponseEntity.ok(this.modelAIService.generateMoviesSuggestion(suggestRequestDto.userPreferences()));

    }

    // Put (update) Method:
    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable long id, @RequestBody UpdateMovieDto updateMovieDto) {
        return ResponseEntity.ok(this.movieService.update(id, updateMovieDto));
    }

    // Delete Method:
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        boolean deleted = movieService.deleteById(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
