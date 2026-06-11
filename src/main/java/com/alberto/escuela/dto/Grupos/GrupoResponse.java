package com.alberto.escuela.dto.Grupos;

import com.alberto.escuela.dto.datos.DatosAula;
import com.alberto.escuela.dto.datos.DatosCursos;
import com.alberto.escuela.dto.datos.DatosMaestro;

import java.util.List;

public record GrupoResponse(
        Long id,
        DatosCursos curso,
        DatosMaestro maestro,
        DatosAula aula,
        List<String> horarios,
        String periodo
) {
}
