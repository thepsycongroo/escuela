package com.alberto.escuela.emuns;

import com.alberto.escuela.exceptions.RecursoNoEncontradoException;
import com.alberto.escuela.utils.StringCustomUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DiaSemana {
    LUNES("Lunes"),
    MARTES("Martes"),
    MIERCOLES("Miércoles"),
    JUEVES("Jueves"),
    VIERNES("Viernes"),
    SABADO("Sábado");

    private final String descripcion;

    @JsonCreator
    public static DiaSemana from(String descripcion) {
        return obtenerDiaSemanaPorDescripcion(descripcion);
    }

    public static DiaSemana obtenerDiaSemanaPorDescripcion(String descripcion) {

        StringCustomUtils.validarNoVacio(
                descripcion,
                "La descripcion es requerida"
        );

        String descripcionNormalizada =
                StringCustomUtils.quitarAcentos(descripcion.trim());

        for (DiaSemana diaSemana : values()) {

            if (StringCustomUtils.quitarAcentos(diaSemana.descripcion)
                    .equalsIgnoreCase(descripcionNormalizada)) {

                return diaSemana;
            }
        }

        throw new RecursoNoEncontradoException(
                "No existe un día de la semana con la descripción: "
                        + descripcion
        );
    }
}
