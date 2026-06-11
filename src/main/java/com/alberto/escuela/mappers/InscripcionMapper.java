package com.alberto.escuela.mappers;

import com.alberto.escuela.dto.Inscripciones.InscripcionRequest;
import com.alberto.escuela.dto.Inscripciones.InscripcionResponse;
import com.alberto.escuela.dto.datos.DatosAlumno;
import com.alberto.escuela.dto.datos.DatosInscripcion;
import com.alberto.escuela.entities.Alumno;
import com.alberto.escuela.entities.Grupo;
import com.alberto.escuela.entities.Inscripcion;
import com.alberto.escuela.utils.StringCustomUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class InscripcionMapper implements CommonMapper<InscripcionRequest, InscripcionResponse, Inscripcion>{
private final AlumnoMapper alumnoMapper;
private final GrupoMapper grupoMapper;

    @Override
    public Inscripcion requestEntidad(InscripcionRequest request) {
        if (request==null) return null;
        return Inscripcion.builder()
                .alumno(Alumno.builder().idAlumno(request.idAlumno()).build())
                .grupo(Grupo.builder().idGrupo(request.idGrupo()).build())
                .build();
    }

    @Override
    public InscripcionResponse entidadResponse(Inscripcion entidad) {
        if (entidad==null)return null;

        return new InscripcionResponse(entidad.getIdInscripcion(),
                alumnoMapper.entidadADatosAlumno(entidad.getAlumno()),
                grupoMapper.entidadADatosGrupo(entidad.getGrupo()),
                entidadACalificacion(entidad),
                StringCustomUtils.localDateAString(entidad.getFechaInscripcion()));
    }

    private BigDecimal entidadACalificacion(Inscripcion entidad){
        if(entidad.getCalificacion() == null) return null;
        return entidad.getCalificacion().getCalificacion();
    }
    public DatosInscripcion entidadAdatosInscripcion(Inscripcion entidad){
        if (entidad==null) return null;

        return new DatosInscripcion(
                alumnoMapper.entidadADatosAlumno(entidad.getAlumno()),
                grupoMapper.entidadADatosGrupo(entidad.getGrupo()),
                StringCustomUtils.localDateAString(entidad.getFechaInscripcion()));
    }
}
