package com.model.api.persistence;

import com.model.api.domain.dto.MovieDto;
import com.model.api.domain.dto.UpdateMovieDto;
import com.model.api.domain.exception.MovieAlreadyExistsException;
import com.model.api.domain.exception.MovieDoesNotExistsExeption;
import com.model.api.domain.repository.MovieRepository;
import com.model.api.persistence.crud.CrudMovieEntity;
import com.model.api.persistence.entity.MovieEntity;
import com.model.api.persistence.mapper.MovieMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieEntityRepository implements MovieRepository {
    private final CrudMovieEntity crudMovieEntity;
    private final MovieMapper movieMapper;

    public MovieEntityRepository(CrudMovieEntity crudMovieEntity, MovieMapper movieMapper) {
        this.crudMovieEntity = crudMovieEntity;
        this.movieMapper = movieMapper;
    }

    // Get Method:
    @Override
    public List<MovieDto> getAll() {
        return this.movieMapper.toDto(this.crudMovieEntity.findAll());
    }

    @Override
    public MovieDto getById(long id) {
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);
        return this.movieMapper.toDto(movieEntity);
    }

    // Post Method:
    @Override
    public MovieDto save(@Valid MovieDto movieDto) {

        // Exception control - unique title:
        if (this.crudMovieEntity.findFirstByTitulo(movieDto.title()) != null) {
            throw new MovieAlreadyExistsException(movieDto.title());
        }

        MovieEntity movieEntity = this.movieMapper.toEntity(movieDto);
        movieEntity.setEstado("D");

        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));
    }

    // Put Method
    @Override
    public MovieDto update(long id, UpdateMovieDto updateMovieDto) {

        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElseThrow(() -> new MovieDoesNotExistsExeption(id));

        // Conversiones directas:
        /*
        movieEntity.setTitulo(updateMovieDto.title());
        movieEntity.setFechaEstreno(updateMovieDto.releaseDate());
        movieEntity.setClasificacion(BigDecimal.valueOf(updateMovieDto.rating()));
        movieEntity.setEstado(String.valueOf(updateMovieDto.available()));
        */

        // Traemos las conversiones de MovieMapper:
        this.movieMapper.updateEntityFromDto(updateMovieDto, movieEntity);

        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));
    }

    // Delete Method
    public boolean deleteById(long id) {

        Optional<MovieEntity> movie = Optional.ofNullable(crudMovieEntity.findById(id).orElseThrow(() -> new MovieDoesNotExistsExeption(id)));

        if (movie.isEmpty()) return false;

        crudMovieEntity.deleteById(id);
        return true;
    }
}
