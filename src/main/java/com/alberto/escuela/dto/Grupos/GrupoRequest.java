package com.alberto.escuela.dto.Grupos;

import jakarta.validation.constraints.*;

public record GrupoRequest(
        @NotNull(message = "El id curso es requerido")
        @Positive(message = "El id curso debe ser positvo")
        Long idCurso,
        @NotNull(message = "El id maestro es requerido")
        @Positive(message = "El id maestro debe ser positvo")
        Long idMaestro,
        @NotNull(message = "El id aula es requerido")
        @Positive(message = "El id aula debe ser positvo")
        Long idAula,
        @NotBlank(message = "El periodo es requerido")
        @Size(min = 1, max = 20, message = "El periodod debe tener de 1 a 20 caracteres")
        @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])$", message = "El periodo debe tener el formato YYYY-MM")
        String periodo
) {
}
