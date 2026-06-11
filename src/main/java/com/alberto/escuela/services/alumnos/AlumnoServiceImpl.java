package com.alberto.escuela.services.alumnos;

import com.alberto.escuela.dto.Alumnos.AlumnoRequest;
import com.alberto.escuela.dto.Alumnos.AlumnoResponse;
import com.alberto.escuela.entities.Alumno;
import com.alberto.escuela.mappers.AlumnoMapper;
import com.alberto.escuela.repositories.AlumnoRepository;
import com.alberto.escuela.repositories.InscripcionRepository;
import com.alberto.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j

public class AlumnoServiceImpl implements AlumnoService{
    private final AlumnoRepository alumnoRepository;
    private final AlumnoMapper alumnoMapper;
    private final InscripcionRepository inscripcionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoResponse> listar() {
        return alumnoRepository.findAll().stream().map(alumnoMapper::entidadResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AlumnoResponse obtenerPorId(Long id) {
        return alumnoMapper.entidadResponse(obtenerAlumno(id));
    }

    @Override
    public AlumnoResponse registrar(AlumnoRequest request) {
        if (request == null)
        return null;

        Alumno alumno = alumnoMapper.requestEntidad(request);
        alumno.setMatricula(alumnoRepository.obtenerMatricula(request.nombre(),request.apellidoPaterno(),request.apellidoMaterno()));
        alumno.setEmail(alumnoRepository.obtenerCorreo(request.nombre(),request.apellidoPaterno(),request.apellidoMaterno()));
        alumno.setFechaIngreso(LocalDate.now());
        alumnoRepository.save(alumno);
        return alumnoMapper.entidadResponse(alumno);

    }

    @Override
    public AlumnoResponse actualizar(AlumnoRequest request, Long id) {
        Alumno alumno= obtenerAlumno(id);
        String matricula = alumnoRepository.obtenerMatricula(request.nombre().trim(),request.apellidoPaterno().trim(),request.apellidoMaterno().trim());
        String email = alumnoRepository.obtenerCorreo(request.nombre().trim(),request.apellidoPaterno().trim(),request.apellidoMaterno().trim());
        alumno.actualizar(request.nombre(),
                request.apellidoPaterno(),
                request.apellidoMaterno(),
                email,
                matricula
                );
        return alumnoMapper.entidadResponse(alumno);
    }

    @Override
    public void eliminar(Long id) {
        Alumno alumno = obtenerAlumno(id);
        if(inscripcionRepository.existsByAlumno_IdAlumno(id))
            throw new IllegalStateException("Existen inscripciones para este alumno");

        alumnoRepository.delete(alumno);
    }

    private Alumno obtenerAlumno(Long id){
        return ServiceUtils.obtenerEntidadOException(alumnoRepository,id,Alumno.class);
    }

}
