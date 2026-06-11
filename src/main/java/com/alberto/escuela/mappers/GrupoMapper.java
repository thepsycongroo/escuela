package com.alberto.escuela.mappers;

import com.alberto.escuela.dto.Grupos.GrupoRequest;
import com.alberto.escuela.dto.Grupos.GrupoResponse;
import com.alberto.escuela.dto.datos.DatosAula;
import com.alberto.escuela.dto.datos.DatosCursos;
import com.alberto.escuela.dto.datos.DatosGrupo;
import com.alberto.escuela.dto.datos.DatosMaestro;
import com.alberto.escuela.entities.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@AllArgsConstructor
public class GrupoMapper implements CommonMapper<GrupoRequest, GrupoResponse, Grupo>{
    private final CursoMapper cursoMapper;
    private final MaestroMapper maestroMapper;
    private final AulaMapper aulaMapper;
    private final HorariosMapper horariosMapper;


    @Override
    public Grupo requestEntidad(GrupoRequest request) {
        return Grupo.builder()
                .curso(Curso.builder().idCurso(request.idCurso()).build())
                .maestro(Maestro.builder().idMaestro(request.idMaestro()).build())
                .aula(Aula.builder().idAula(request.idAula()).build())
                .periodo(request.periodo())
                .build();
    }

    @Override
    public GrupoResponse entidadResponse(Grupo entidad) {
        return new GrupoResponse(entidad.getIdGrupo(),entidadADatosCursos(entidad),
                entidadADatosMaestro(entidad),
                entidadADatosAula(entidad),
                entidadADatosHorarios(entidad),
                entidad.getPeriodo());
    }

    private DatosCursos entidadADatosCursos(Grupo entidad){
        if (entidad== null) return null;
        return cursoMapper.entidadADatosCurso(entidad.getCurso());
    }
    private DatosMaestro entidadADatosMaestro(Grupo entidad){
        if (entidad== null) return null;
        return maestroMapper.entidadADatosMaestros(entidad.getMaestro());
    }
    private DatosAula entidadADatosAula(Grupo entidad){
        if (entidad== null) return null;
        return aulaMapper.entidadADatosAula(entidad.getAula());
    }
    private List<String> entidadADatosHorarios(Grupo entidad){
        if (entidad== null) return null;
        return entidad.getHorarios().stream().sorted(Comparator.comparing(Horario::getDia).thenComparing(Horario::getHoraInicio)).map(horariosMapper::entidadADatosHorario).toList();
    }

    public DatosGrupo entidadADatosGrupo(Grupo grupo){
        if (grupo==null) return null;

        return new DatosGrupo(grupo.getCurso().getNombre(),grupo.getMaestro().getNombre(),grupo.getAula().getNombre(),grupo.getPeriodo());
    }





}
