package com.alberto.escuela.repositories;

import com.alberto.escuela.entities.Maestro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaestrosRepository extends JpaRepository<Maestro,Long> {
}
