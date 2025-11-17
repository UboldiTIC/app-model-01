package com.model.api.persistence.mapper;

import org.mapstruct.Named;

public class AvailableMapper {

    @Named("stringToBoolean")
    public static Boolean stringToBoolean (String estado){
        if (estado == null) return null;

        return switch (estado.toUpperCase()) {
            case "D" -> true;
            case "N" -> false;
            default -> null;
        };
    }

    @Named("booleanToString")
    public static String booleanToString (Boolean available) {
        if (available == null) return null;
        return available ? "D" : "N";
    }
}
