package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record RegistroAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fechaNacimiento,
        @JsonAlias("death_year") Integer fechaFallecimiento) {
}
