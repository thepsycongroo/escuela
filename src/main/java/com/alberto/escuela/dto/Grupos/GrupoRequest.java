package com.alberto.escuela.dto.Grupos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record GrupoRequest(
        @NotNull(message = "El id curso es requerido")
        Long idCurso,
        @NotNull(message = "El id maestro es requerido")
        Long idMaestro,
        @NotNull(message = "El id aula es requerido")
        Long idAula,
        @NotBlank(message = "El periodo es requerido")
        @Size(min = 1, max = 20, message = "El periodod debe tener de 1 a 20 caracteres")
        String periodo
) {
}
