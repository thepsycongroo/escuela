package com.alberto.escuela.dto.Cursos;

public record CursoResponse(
        Long id,
        String nombre,
        String descripcion,
        Integer creditos
) {
}
