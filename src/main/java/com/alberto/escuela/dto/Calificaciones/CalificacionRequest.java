package com.alberto.escuela.dto.Calificaciones;

import com.alberto.escuela.dto.Alumnos.AlumnoRequest;
import com.alberto.escuela.dto.Grupos.GrupoRequest;
import com.alberto.escuela.entities.Alumno;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record CalificacionRequest(
    @NotNull(message = "La inscripción es requerida")
    @Positive(message = "El id inscripción debe ser positivo")
    Long idInscripcion,

    @NotNull(message = "La calificación es requerida")
    @DecimalMin(value = "1.00", message = "La calificación mínima es 1")
    @DecimalMax(value = "10.00", message = "La calificación máxima es 10")
    BigDecimal calificacion
) {
}
