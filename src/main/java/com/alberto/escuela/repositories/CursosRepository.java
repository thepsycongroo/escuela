package com.alberto.escuela.repositories;

import com.alberto.escuela.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursosRepository extends JpaRepository<Curso,Long> {
}
