package com.alberto.escuela.dto.Inscripciones;

import com.alberto.escuela.dto.Alumnos.AlumnoResponse;
import com.alberto.escuela.dto.Grupos.GrupoResponse;
import com.alberto.escuela.dto.datos.DatosAlumno;
import com.alberto.escuela.dto.datos.DatosGrupo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public record InscripcionResponse(
        Long id,
        DatosAlumno alumno,
        DatosGrupo grupo,
        BigDecimal calificacion,
        String fechaInscripcion
) {
}
