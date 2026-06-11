package com.alberto.escuela.services.GruposService;

import com.alberto.escuela.dto.Grupos.GrupoRequest;
import com.alberto.escuela.dto.Grupos.GrupoResponse;
import com.alberto.escuela.entities.Aula;
import com.alberto.escuela.entities.Curso;
import com.alberto.escuela.entities.Grupo;
import com.alberto.escuela.entities.Maestro;
import com.alberto.escuela.exceptions.RecursoNoEncontradoException;
import com.alberto.escuela.mappers.GrupoMapper;
import com.alberto.escuela.repositories.*;
import com.alberto.escuela.utils.ServiceUtils;
import com.alberto.escuela.utils.ValoresNumericosUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@AllArgsConstructor

public class GrupoServiceImpl implements GrupoService{
    private final GrupoRepository grupoRepository;
    private final GrupoMapper grupoMapper;

    private final MaestroRepository maestroRepository;
    private final CursoRepository cursoRepository;
    private final AulaRepository aulaRepository;
    private final HorarioRepository horarioRepository;
    private final InscripcionRepository inscripcionRepository;



    @Override
    @Transactional(readOnly = true)
    public List<GrupoResponse> listar() {
        return grupoRepository.findAll().stream().map(grupoMapper::entidadResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public GrupoResponse obtenerPorId(Long id) {
        return grupoMapper.entidadResponse(obtenerGrupo(id));
    }

    @Override
    public GrupoResponse registrar(GrupoRequest request) {
        Grupo grupo = grupoMapper.requestEntidad(request);

        if (grupoRepository.existsByCurso_IdCursoAndMaestroIdMaestroAndAula_IdAulaAndPeriodo(
                request.idCurso(), request.idMaestro(), request.idAula(), request.periodo().trim()
        ))
            throw new IllegalStateException("Ya existe un grupo registrado con la misma informacion");

        Maestro maestro = maestroRepository.findById(request.idMaestro()).orElseThrow(()-> new RecursoNoEncontradoException("Id maestro no valido"));
        Curso curso = cursoRepository.findById(request.idCurso()).orElseThrow(()-> new RecursoNoEncontradoException("Id curso no valido"));
        Aula aula = aulaRepository.findById(request.idAula()).orElseThrow(()-> new RecursoNoEncontradoException("Id aula no valido"));
        grupo.setCurso(curso);
        grupo.setMaestro(maestro);
        grupo.setAula(aula);
        grupoRepository.save(grupo);
        return grupoMapper.entidadResponse(grupo);
    }

    @Override
    public GrupoResponse actualizar(GrupoRequest request, Long id) {
        Grupo grupo = obtenerGrupo(id);

        if (grupoRepository.existsByIdGrupoNotAndCurso_IdCursoAndMaestro_IdMaestroAndAula_IdAulaAndPeriodo(
                id, request.idCurso(), request.idMaestro(), request.idAula(), request.periodo()
        ))
            throw new IllegalArgumentException("Ya existe un grupo registrado con la misma informacion");

        Maestro maestro = maestroRepository.findById(request.idMaestro()).orElseThrow(()-> new RecursoNoEncontradoException("Id maestro no valido"));
        Curso curso = cursoRepository.findById(request.idCurso()).orElseThrow(()-> new RecursoNoEncontradoException("Id curso no valido"));
        Aula aula = aulaRepository.findById(request.idAula()).orElseThrow(()-> new RecursoNoEncontradoException("Id aula no valido"));

        grupo.setCurso(curso);
        grupo.setAula(aula);
        grupo.setMaestro(maestro);
        grupo.actualizar(request.periodo());

        return grupoMapper.entidadResponse(grupo);
    }

    @Override
    public void eliminar(Long id) {
        Grupo grupo = obtenerGrupo(id);

        if (horarioRepository.existsByGrupo_IdGrupo(id))
            throw new IllegalStateException("El grupo tiene horarios asignados");
        if (inscripcionRepository.existsByGrupo_IdGrupo(id))
            throw new IllegalStateException("El grupo tiene inscripciones registradas");

        grupoRepository.delete(grupo);
    }

    private Grupo obtenerGrupo(Long id){
        return ServiceUtils.obtenerEntidadOException(grupoRepository,id,Grupo.class);
    }
}
