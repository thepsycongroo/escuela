package com.alberto.escuela.repositories;

import com.alberto.escuela.entities.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalificacionesRepository extends JpaRepository<Calificacion,Long> {
}
