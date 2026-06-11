package com.alberto.escuela.mappers;

import com.alberto.escuela.dto.Alumnos.AlumnoRequest;
import com.alberto.escuela.dto.Alumnos.AlumnoResponse;
import com.alberto.escuela.dto.datos.DatosAlumno;
import com.alberto.escuela.dto.datos.DatosCalificaciones;
import com.alberto.escuela.entities.Alumno;
import com.alberto.escuela.entities.Calificacion;
import com.alberto.escuela.utils.StringCustomUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class AlumnoMapper implements CommonMapper<AlumnoRequest, AlumnoResponse, Alumno> {

    @Override
    public Alumno requestEntidad(AlumnoRequest request) {
        if (request==null) return null;

        return Alumno.builder().nombre(request.nombre())
                .apellidoPaterno(request.apellidoPaterno())
                .apellidoMaterno(request.apellidoMaterno())
        .build();
    }

    @Override
    public AlumnoResponse entidadResponse(Alumno entidad) {
        if (entidad==null) return null;

        List<DatosCalificaciones> datosCalificaciones = this.entidadADatosCalificaciones(entidad);
        return new AlumnoResponse(entidad.getIdAlumno(),
                String.join(" ", entidad.getNombre(), entidad.getApellidoPaterno(),entidad.getApellidoMaterno()),
                entidad.getEmail(),
                entidad.getMatricula(),
                StringCustomUtils.localDateAString(entidad.getFechaIngreso()),
                datosCalificaciones,
                entidad.calculaTotalCalificacion()
                );
    }

    private List<DatosCalificaciones> entidadADatosCalificaciones(Alumno entidad){
        if (entidad == null) return null;

        return entidad.getInscripciones().stream().map(inscripcion->entidadADatosCalificaciones(inscripcion.getCalificacion())).toList();
    }

    public DatosAlumno entidadADatosAlumno(Alumno entidad){
        if (entidad == null) return null;
        return new DatosAlumno(entidad.getNombre(),entidad.getMatricula(),entidad.getEmail(), StringCustomUtils.localDateAString(entidad.getFechaIngreso()));
    }


    private DatosCalificaciones entidadADatosCalificaciones(Calificacion entidad){
        if (entidad==null) return null;
        return new DatosCalificaciones(entidad.getInscripcion().getGrupo().getCurso().getNombre(),
                entidad.getInscripcion().getGrupo().getPeriodo(),
                entidad.getCalificacion());
    }








}
