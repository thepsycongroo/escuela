package com.alberto.escuela.dto.Alumnos;

import com.alberto.escuela.dto.datos.DatosCalificaciones;

import java.math.BigDecimal;
import java.util.List;

public record AlumnoResponse(
        Long id,
        String nombre,
        String email,
        String matricula,
        String fechaIngreso,
        List<DatosCalificaciones> calificaciones,
        BigDecimal promedio) {
}
