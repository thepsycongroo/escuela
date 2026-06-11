package com.alberto.escuela.dto.Alumnos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlumnoRequest(
        @NotBlank(message = "El nombre es requerido")
        @Size(min = 4, max = 50,message = "El nombre debe tener de 4 a 50 caracteres")
        String nombre,
        @NotBlank(message = "El apellido paterno es requerido")
        @Size(min = 4, max = 50,message = "El apellido paterno debe tener de 4 a 50 caracteres")
        String apellidoPaterno,
        @NotBlank(message = "El apellido materno es requerido")
        @Size(min = 4, max = 50,message = "El apellido materno debe tener de 4 a 50 caracteres")
        String apellidoMaterno
) {
}
