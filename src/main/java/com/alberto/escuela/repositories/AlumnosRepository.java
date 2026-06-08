package com.alberto.escuela.repositories;

import com.alberto.escuela.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnosRepository extends JpaRepository<Alumno,Long> {
}
