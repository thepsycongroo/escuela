package com.alberto.escuela.services.calificaciones;

import com.alberto.escuela.dto.Calificaciones.CalificacionRequest;
import com.alberto.escuela.dto.Calificaciones.CalificacionResponse;
import com.alberto.escuela.entities.Calificacion;
import com.alberto.escuela.entities.Inscripcion;
import com.alberto.escuela.exceptions.RecursoNoEncontradoException;
import com.alberto.escuela.mappers.CalificacionMapper;
import com.alberto.escuela.repositories.CalificacionRepository;
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
public class CalificacionServiceImpl implements CalificacionService{
    private final CalificacionRepository calificacionRepository;
    private final CalificacionMapper calificacionMapper;
    private final InscripcionRepository inscripcionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CalificacionResponse> listar() {
        return calificacionRepository.findAll().stream().map(calificacionMapper::entidadResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CalificacionResponse obtenerPorId(Long id) {
        return calificacionMapper.entidadResponse(obtenerCalificacion(id));
    }

    @Override
    public CalificacionResponse registrar(CalificacionRequest request) {
        Calificacion calificacion = calificacionMapper.requestEntidad(request);
        Inscripcion inscripcion = inscripcionRepository.findById(request.idInscripcion()).orElseThrow(()-> new RecursoNoEncontradoException("No se encontro la inscripcion con id "+request.idInscripcion()));

        if (calificacionRepository.existsByInscripcion_IdInscripcion(request.idInscripcion()))
            throw new IllegalStateException("Ya existe una calificacion para la inscripcion");

        calificacion.setInscripcion(inscripcion);
        calificacionRepository.save(calificacion);
        calificacion.setFechaRegistro(LocalDate.now());

        return calificacionMapper.entidadResponse(calificacion);
    }

    @Override
    public CalificacionResponse actualizar(CalificacionRequest request, Long id) {
        Calificacion calificacion = obtenerCalificacion(id);
        Inscripcion inscripcion = inscripcionRepository.findById(request.idInscripcion()).orElseThrow(()-> new RecursoNoEncontradoException("No se encontro la inscripcion con id "+request.idInscripcion()));

        if (calificacionRepository.existsByInscripcion_IdInscripcionAndIdCalificacionNot(request.idInscripcion(),id))
            throw new IllegalStateException("Ya existe una calificacion para la inscripcion");

        calificacion.setInscripcion(inscripcion);
        calificacion.setCalificacion(request.calificacion());
        calificacion.setFechaRegistro(LocalDate.now());
        return calificacionMapper.entidadResponse(calificacion);
    }

    @Override
    public void eliminar(Long id) {
        Calificacion calificacion = obtenerCalificacion(id);
        calificacionRepository.delete(calificacion);
    }

    private Calificacion obtenerCalificacion(Long id){
        return ServiceUtils.obtenerEntidadOException(calificacionRepository,id,Calificacion.class);
    }

}
