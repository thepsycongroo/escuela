package com.alberto.escuela.services.cursos;

import com.alberto.escuela.dto.Cursos.CursoRequest;
import com.alberto.escuela.dto.Cursos.CursoResponse;
import com.alberto.escuela.entities.Curso;
import com.alberto.escuela.mappers.CursoMapper;
import com.alberto.escuela.repositories.CursoRepository;
import com.alberto.escuela.repositories.GrupoRepository;
import com.alberto.escuela.utils.ServiceUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CursoServiceImpl implements CursoService{
    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;
    private final GrupoRepository grupoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CursoResponse> listar() {
        return cursoRepository.findAll().stream().map(cursoMapper::entidadResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CursoResponse obtenerPorId(Long id) {
        return cursoMapper.entidadResponse(obtenerCurso(id));
    }

    @Override
    public CursoResponse registrar(CursoRequest request) {
        Curso curso = cursoMapper.requestEntidad(request);

        if (cursoRepository.existsByNombreIgnoreCase(request.nombre().trim()))
            throw  new IllegalArgumentException("El nombre del curso ya existe");

        cursoRepository.save(curso);
        return cursoMapper.entidadResponse(curso);
    }

    @Override
    public CursoResponse actualizar(CursoRequest request, Long id) {
        Curso curso = obtenerCurso(id);
        if (cursoRepository.existsByNombreIgnoreCaseAndIdCursoNot(request.nombre().trim(),id))
            throw new IllegalArgumentException("El nombre del curso ya existe");
        curso.actualizar(request.nombre(), request.descripcion(), request.creditos());

        return cursoMapper.entidadResponse(curso);
    }

    @Override
    public void eliminar(Long id) {
        Curso curso = obtenerCurso(id);
        if (grupoRepository.existsByCurso_IdCurso(id))
            throw  new IllegalStateException("El curso tiene grupos asigandos");

        cursoRepository.delete(curso);
    }

    private Curso obtenerCurso(Long id){
        return ServiceUtils.obtenerEntidadOException(cursoRepository,id,Curso.class);
    }

}
