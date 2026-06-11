package com.alberto.escuela.dto.Calificaciones;

import com.alberto.escuela.dto.Inscripciones.InscripcionResponse;
import com.alberto.escuela.dto.datos.DatosInscripcion;

import java.math.BigDecimal;
import java.util.List;

public record CalificacionResponse(
        Long id,
        DatosInscripcion inscripcion,
        BigDecimal calificacion,
        String fechaRegistro
) {
}
