package com.alberto.escuela.repositories;

import com.alberto.escuela.entities.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorariosRepository extends JpaRepository<Horario,Long> {
}
