package com.alberto.escuela.dto.Maestros;

import com.alberto.escuela.dto.Cursos.CursoResponse;
import com.alberto.escuela.dto.datos.DatosCursos;

import java.util.List;

public record MaestroResponse(Long id,
                              String nombre,
                              String email,
                              String telefono,
                              List<DatosCursos> cursos) {

}
