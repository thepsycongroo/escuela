package com.alberto.escuela.repositories;

import com.alberto.escuela.entities.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AulasRepository extends JpaRepository<Aula,Long> {
}
