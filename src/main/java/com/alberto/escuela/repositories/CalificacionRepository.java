package com.alberto.escuela.repositories;

import com.alberto.escuela.entities.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalificacionRepository extends JpaRepository<Calificacion,Long> {

    boolean existsByIdCalificacion(Long idCalificacion);

    boolean existsByInscripcion_IdInscripcion(Long IdInscripcion);

    boolean existsByInscripcion_IdInscripcionAndIdCalificacionNot(Long inscripcionIdInscripcion, Long idCalificacion);

}
