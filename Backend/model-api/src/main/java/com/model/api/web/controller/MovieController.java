package com.model.api.web.controller;

import com.model.api.domain.dto.MovieDto;
import com.model.api.domain.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> getAll() {
        return this.movieService.getAll();
    }

    @GetMapping("/{id}")
    public MovieDto getById(@PathVariable long id){
        return this.movieService.getById(id);
    }
}
