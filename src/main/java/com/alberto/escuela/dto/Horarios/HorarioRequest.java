package com.alberto.escuela.dto.Horarios;

import com.alberto.escuela.dto.Grupos.GrupoRequest;
import com.alberto.escuela.emuns.DiaSemana;
import jakarta.validation.constraints.*;

import java.util.List;

public record HorarioRequest(
        @NotNull(message = "El grupo es requerido")
        @Positive(message = "El id grupo debe ser positivo")
        Long idGrupo,
        @NotNull(message = "El día es requerido")
        DiaSemana dia,
        @NotBlank(message = "La hora inicio es requerida")
        @Pattern(regexp = "^([01][0-9]|2[0-3]):[0-5][0-9]$", message = "La hora debe tener un formato válido de 24 horas (HH:mm). Ejemplo: 08:00")
        String horaInicio,
        @NotBlank(message = "La hora fin es requerida")
        @Pattern(regexp = "^([01][0-9]|2[0-3]):[0-5][0-9]$", message = "La hora debe tener un formato válido de 24 horas (HH:mm). Ejemplo: 08:00")
        String horaFin
) {
}
