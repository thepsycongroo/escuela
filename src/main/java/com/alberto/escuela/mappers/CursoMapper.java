package com.alberto.escuela.mappers;

import com.alberto.escuela.dto.Cursos.CursoRequest;
import com.alberto.escuela.dto.Cursos.CursoResponse;
import com.alberto.escuela.dto.datos.DatosCursos;
import com.alberto.escuela.entities.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper implements CommonMapper<CursoRequest, CursoResponse, Curso> {

    @Override
    public Curso requestEntidad(CursoRequest request) {
        if (request==null)
        return null;
        return Curso.builder().nombre(request.nombre()).descripcion(request.descripcion()).creditos(request.creditos()).build();
    }

    @Override
    public CursoResponse entidadResponse(Curso entidad) {
        if (entidad==null)
        return null;

        return new CursoResponse(entidad.getIdCurso(), entidad.getNombre(),entidad.getDescripcion(),entidad.getCreditos());
    }

    public DatosCursos entidadADatosCurso(Curso entidad){
        if(entidad == null)
            return  null;

        String descripcion = entidad.getDescripcion() ==null? "Sin descripcion":entidad.getDescripcion();

        return new DatosCursos(entidad.getNombre(),descripcion,entidad.getCreditos());
    }
}
