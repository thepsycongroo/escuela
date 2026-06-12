package com.alberto.escuela.mappers;

import com.alberto.escuela.dto.Maestros.MaestroRequest;
import com.alberto.escuela.dto.Maestros.MaestroResponse;
import com.alberto.escuela.dto.datos.DatosCursos;
import com.alberto.escuela.dto.datos.DatosMaestro;
import com.alberto.escuela.entities.Curso;
import com.alberto.escuela.entities.Maestro;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class MaestroMapper implements CommonMapper<MaestroRequest,MaestroResponse, Maestro> {

    private final CursoMapper cursoMapper;

    @Override
    public Maestro requestEntidad(MaestroRequest request) {
        if (request ==null)
        return null;

        return Maestro.builder()
                .nombre(request.nombre())
                .apellidoPaterno(request.apellidoPaterno())
                .apellidoMaterno(request.apellidoMaterno())
                .telefono(request.telefono())
                .email(request.email())
                .build();
    }

    @Override
    public MaestroResponse entidadResponse(Maestro entidad) {
        if (entidad ==null)
        return null;

        List<DatosCursos> cursos = this.entidadADatosCurso(entidad);
        return new MaestroResponse(
                entidad.getIdMaestro(),
                String.join(" ", entidad.getNombre(), entidad.getApellidoPaterno(), entidad.getApellidoMaterno()),
                entidad.getEmail(),
                entidad.getTelefono(),
                cursos

        );
    }

    private List<DatosCursos> entidadADatosCurso(Maestro entidad){
        if (entidad== null) return List.of();

        return entidad.getGrupos().stream().map(grupo -> cursoMapper.entidadADatosCurso(grupo.getCurso())).toList();
    }

    public DatosMaestro entidadADatosMaestros(Maestro entidad){
        return new DatosMaestro(String.join(" ", entidad.getNombre(), entidad.getApellidoPaterno(), entidad.getApellidoMaterno()), entidad.getEmail(),entidad.getTelefono());
    }

}
