package com.alberto.escuela.emuns;

import com.alberto.escuela.exceptions.RecursoNoEncontradoException;
import com.alberto.escuela.utils.StringCustomUtils;
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

    public static DiaSemana obtenerDiaSemanaPorDescripcion(String descripcion){
        StringCustomUtils.validarNoVacio(descripcion,"La descripcion es requerida");
        String descripcionNormalizada = StringCustomUtils.quitarAcentos(descripcion.trim());

        for (DiaSemana diaSemana : values()) {
            if (StringCustomUtils.quitarAcentos(diaSemana.descripcion).equalsIgnoreCase(descripcionNormalizada)) ;
            return diaSemana;
        }
        throw new RecursoNoEncontradoException("No existe un estado venta con la descripcion: "+ descripcion);
    }
}
