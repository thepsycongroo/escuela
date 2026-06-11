package com.alberto.escuela.repositories;

import com.alberto.escuela.entities.Maestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaestroRepository extends JpaRepository<Maestro,Long> {

    boolean existsByEmailIgnoreCase(String email);
    boolean existsByTelefono(String telefono);
    boolean existsByEmailIgnoreCaseAndIdMaestroNot(String email, Long id);
    boolean existsByTelefonoAndIdMaestroNot(String telefono, Long id);
}
