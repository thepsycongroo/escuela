package com.alberto.escuela.dto.Aulas;

import jakarta.validation.constraints.*;

public record AulaRequest(
        @NotBlank(message = "El nombre es requerido")
        @Size(min = 3, max = 30 ,message = "El nombre debe tener de 3 a 30 caracteres")
        String nombre,
        @NotNull(message = "La capacidad es requerida")
        @Min(value = 1, message = "La capacidad minima es 1")
        @Max(value = 9999, message = "La capacidad maxima es de 9999")
        Integer capacidad
        ) {
}
