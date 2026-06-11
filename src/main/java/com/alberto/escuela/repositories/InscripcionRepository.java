package com.alberto.escuela.repositories;

import com.alberto.escuela.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion,Long> {
    boolean existsByAlumno_IdAlumno(Long alumnoIdAlumno);

    boolean existsByGrupo_IdGrupo(Long grupoIdGrupo);

    boolean existsByAlumno_IdAlumnoAndGrupo_IdGrupo(Long alumnoIdAlumno, Long grupoIdGrupo);

    boolean existsByAlumno_IdAlumnoAndGrupo_IdGrupoAndIdInscripcionNot(Long alumnoIdAlumno, Long grupoIdGrupo, Long idInscripcion);

    Integer countByGrupo_IdGrupo(Long grupoIdGrupo);

    Integer countByGrupo_IdGrupoAndIdInscripcionNot(Long grupoIdGrupo, Long idInscripcion);
}
