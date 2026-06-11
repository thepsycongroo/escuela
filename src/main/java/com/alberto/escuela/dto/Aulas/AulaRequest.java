package com.alberto.escuela.dto.Aulas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AulaRequest(
        @NotBlank(message = "El nombre es requerido")
        String nombre,
        @NotNull(message = "La capacidad es requerida")
        Integer capacidad
        ) {
}
