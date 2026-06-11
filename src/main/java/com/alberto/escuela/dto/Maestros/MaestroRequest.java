package com.alberto.escuela.dto.Maestros;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MaestroRequest(
        @NotBlank(message = "El nombre es reuqerido")
        @Size(min = 3,max = 50, message = "El nombre debe tener de 3 a 50 caracteres")
        String nombre,
        @NotBlank(message = "El apellido paterno es reuqerido")
        @Size(min = 4,max = 50, message = "El apellido paterno debe tener de 4 a 50 caracteres")
        String apellidoPaterno,
        @NotBlank(message = "El apellido materno es reuqerido")
        @Size(min = 4,max = 50, message = "El apellido materno debe tener de 4 a 50 caracteres")
        String apellidoMaterno,
        @NotBlank(message = "El email es reuqerido")
        @Size(min = 4,max = 50, message = "El email debe tener de 8 a 100 caracteres")
        @Email(message = "El email debe tener un formato valido (ejemplo@dominio.com)")
        String email,
        @NotBlank(message = "El telefono es requerido")
        @Size(min = 10,max = 10, message = "El telefono debe tener de 10 caracteres")
        @Pattern(regexp = "^[0-9]{10}$",message = "El formato de telefono es invalido (ejemplo 1234567890)")
        String telefono) {
}
