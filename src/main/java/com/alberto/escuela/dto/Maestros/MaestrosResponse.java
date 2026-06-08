package com.alberto.escuela.dto.Maestros;

public record MaestrosResponse(Long idMaestro,
                               String nombre,
                               String apellidoPaterno,
                               String apellidoMaterno,
                               String email,
                               String telefono) {

}
