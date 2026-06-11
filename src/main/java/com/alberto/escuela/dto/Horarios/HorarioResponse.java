package com.alberto.escuela.dto.Horarios;

import com.alberto.escuela.dto.datos.DatosGrupo;

public record HorarioResponse(
        Long id,
        DatosGrupo grupo,
        String horario
) {
}
