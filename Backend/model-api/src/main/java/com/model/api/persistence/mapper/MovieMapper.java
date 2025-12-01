package com.model.api.persistence.mapper;

import com.model.api.domain.dto.MovieDto;
import com.model.api.domain.dto.UpdateMovieDto;
import com.model.api.persistence.entity.MovieEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenreMapper.class, AvailableMapper.class})
public interface MovieMapper {
    @Mapping(source = "titulo", target = "title")
    @Mapping(source = "duracion", target = "duration")
    @Mapping(source = "genero", target = "genre", qualifiedByName = "stringToGenre")
    @Mapping(source = "fechaEstreno", target = "releaseDate")
    @Mapping(source = "clasificacion", target = "rating")
    @Mapping(source = "estado", target = "available", qualifiedByName = "stringToBoolean")
    MovieDto toDto(MovieEntity entity);
    List<MovieDto> toDto(Iterable<MovieEntity> entities);

    //Post Method:
    @InheritInverseConfiguration // Con esta anotación no tenemos que hacer nuevamente todos los mapping, los hereda.
    @Mapping(source = "genre", target = "genero", qualifiedByName = "genreToString")
    @Mapping(source = "available", target = "estado", qualifiedByName = "booleanToString")
    MovieEntity toEntity(MovieDto dto);


    // Put Method:
    @InheritInverseConfiguration
    //@Mapping(source = "id", target = "id")
    //@Mapping(source = "title", target = "titulo")
    //@Mapping(source = "duration", target = "duracion")
    //@Mapping(source = "genre", target = "genero")
    @Mapping(source = "releaseDate", target = "fechaEstreno")
    @Mapping(source = "rating", target = "clasificacion")
    @Mapping(source = "available", target = "estado", qualifiedByName = "booleanToString")
    void updateEntityFromDto(UpdateMovieDto updateMovieDto, @MappingTarget MovieEntity movieEntity);

    // Delete Method:
    /*
        En una operación DELETE:

         - No recibís datos complejos (solo un id)

         - No actualizás una entidad

         - No creás una entidad nueva

        Por lo tanto no hay nada para mapear hacia la base de datos.
     */

}
