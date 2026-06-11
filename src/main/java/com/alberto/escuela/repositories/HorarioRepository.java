package com.alberto.escuela.repositories;

import com.alberto.escuela.emuns.DiaSemana;
import com.alberto.escuela.entities.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioRepository extends JpaRepository<Horario,Long> {

    boolean existsByGrupo_IdGrupo(Long grupoIdGrupo);

    boolean existsByGrupo_IdGrupoAndDiaAndHoraInicioLessThanAndHoraFinGreaterThan(Long grupoIdGrupo, DiaSemana dia, String horaInicioIsLessThan, String horaFinIsGreaterThan);

}
