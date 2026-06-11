package com.alberto.escuela.services.inscripciones;

import com.alberto.escuela.dto.Inscripciones.InscripcionRequest;
import com.alberto.escuela.dto.Inscripciones.InscripcionResponse;
import com.alberto.escuela.entities.Alumno;
import com.alberto.escuela.entities.Grupo;
import com.alberto.escuela.entities.Inscripcion;
import com.alberto.escuela.exceptions.RecursoNoEncontradoException;
import com.alberto.escuela.mappers.InscripcionMapper;
import com.alberto.escuela.repositories.AlumnoRepository;
import com.alberto.escuela.repositories.CalificacionRepository;
import com.alberto.escuela.repositories.GrupoRepository;
import com.alberto.escuela.repositories.InscripcionRepository;
import com.alberto.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j

public class InscripcionServiceImpl implements InscripcionService{
    private final InscripcionRepository inscripcionRepository;
    private final InscripcionMapper inscripcionMapper;
    private final CalificacionRepository calificacionRepository;
    private final AlumnoRepository alumnoRepository;
    private final GrupoRepository grupoRepository;


    @Override
    public List<InscripcionResponse> listar() {
        return inscripcionRepository.findAll().stream().map(inscripcionMapper::entidadResponse).toList();
    }

    @Override
    public InscripcionResponse obtenerPorId(Long id) {
        return inscripcionMapper.entidadResponse(obtenerInscripcion(id));
    }

    @Override
    public InscripcionResponse registrar(InscripcionRequest request) {
        if (inscripcionRepository.existsByAlumno_IdAlumnoAndGrupo_IdGrupo(request.idAlumno(), request.idGrupo()))
            throw new IllegalArgumentException("La relacion alumno - grupo ya existe");

        Alumno alumno = alumnoRepository.findById(request.idAlumno()).orElseThrow(()-> new RecursoNoEncontradoException("El id de alumno no es valido"));
        Grupo grupo = grupoRepository.findById(request.idGrupo()).orElseThrow(()-> new RecursoNoEncontradoException("El id de grupo no es valido"));

        if (inscripcionRepository.countByGrupo_IdGrupo(grupo.getIdGrupo())>= grupo.getAula().getCapacidad())
            throw new IllegalArgumentException("El aula esta lleno");


        Inscripcion inscripcion= inscripcionMapper.requestEntidad(request);
        inscripcion.setAlumno(alumno);
        inscripcion.setGrupo(grupo);
        inscripcion.setFechaInscripcion(LocalDate.now());
        inscripcionRepository.save(inscripcion);

        return inscripcionMapper.entidadResponse(inscripcion);
    }

    @Override
    public InscripcionResponse actualizar(InscripcionRequest request, Long id) {
        if (inscripcionRepository.existsByAlumno_IdAlumnoAndGrupo_IdGrupoAndIdInscripcionNot(request.idAlumno(), request.idGrupo(),id))
            throw new IllegalArgumentException("La relacion alumno - grupo ya existe");

        Alumno alumno = alumnoRepository.findById(request.idAlumno()).orElseThrow(()-> new RecursoNoEncontradoException("El id de alumno no es valido"));
        Grupo grupo = grupoRepository.findById(request.idGrupo()).orElseThrow(()-> new RecursoNoEncontradoException("El id de grupo no es valido"));

        if (inscripcionRepository.countByGrupo_IdGrupoAndIdInscripcionNot(grupo.getIdGrupo(), id)>= grupo.getAula().getCapacidad())
            throw new IllegalArgumentException("El aula esta lleno");

        Inscripcion inscripcion= obtenerInscripcion(id);
        inscripcion.setFechaInscripcion(LocalDate.now());
        inscripcion.setAlumno(alumno);
        inscripcion.setGrupo(grupo);


        return inscripcionMapper.entidadResponse(inscripcion);
    }

    @Override
    public void eliminar(Long id) {
        Inscripcion inscripcion = obtenerInscripcion(id);

        if (calificacionRepository.existsByInscripcion_IdInscripcion(id))
            throw new IllegalStateException("La inscripcion tiene calificaciones");

        inscripcionRepository.delete(inscripcion);
    }

    private Inscripcion obtenerInscripcion(Long id){
        return ServiceUtils.obtenerEntidadOException(inscripcionRepository,id,Inscripcion.class);
    }

}
