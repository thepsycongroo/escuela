package com.alberto.escuela.repositories;

import com.alberto.escuela.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GruposRepository extends JpaRepository<Grupo,Long> {
}
