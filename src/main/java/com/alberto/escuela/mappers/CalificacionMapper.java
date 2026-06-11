package com.alberto.escuela.mappers;

import com.alberto.escuela.dto.Calificaciones.CalificacionRequest;
import com.alberto.escuela.dto.Calificaciones.CalificacionResponse;
import com.alberto.escuela.dto.datos.DatosCalificaciones;
import com.alberto.escuela.dto.datos.DatosInscripcion;
import com.alberto.escuela.entities.Calificacion;
import com.alberto.escuela.entities.Inscripcion;
import com.alberto.escuela.utils.StringCustomUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CalificacionMapper implements CommonMapper<CalificacionRequest, CalificacionResponse, Calificacion>{
    private final InscripcionMapper inscripcionMapper;

    @Override
    public Calificacion requestEntidad(CalificacionRequest request) {
        if (request==null) return null;

        return Calificacion.builder()
                .inscripcion(Inscripcion.builder().idInscripcion(request.idInscripcion()).build())
                .calificacion(request.calificacion())
                .build();
    }

    @Override
    public CalificacionResponse entidadResponse(Calificacion entidad) {
       if (entidad==null)return null;

       return new CalificacionResponse(entidad.getIdCalificacion(),
               inscripcionMapper.entidadAdatosInscripcion(entidad.getInscripcion()),
               entidad.getCalificacion(),
               StringCustomUtils.localDateAString(entidad.getFechaRegistro()));
    }





}
