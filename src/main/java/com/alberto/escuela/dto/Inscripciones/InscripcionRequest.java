package com.alberto.escuela.dto.Inscripciones;

import com.alberto.escuela.dto.Alumnos.AlumnoRequest;
import com.alberto.escuela.dto.Grupos.GrupoRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record InscripcionRequest(
        @NotNull(message = "El id alumno es requerido")
        @Positive(message = "El id del alumno debe se positivo")
        Long idAlumno,
        @NotNull(message = "El id grupo es requerido")
        @Positive(message = "El id del grupo debe se positivo")
        Long idGrupo
) {
}
