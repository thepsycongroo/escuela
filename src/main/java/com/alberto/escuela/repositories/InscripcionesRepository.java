package com.alberto.escuela.repositories;

import com.alberto.escuela.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionesRepository extends JpaRepository<Inscripcion,Long> {
}
