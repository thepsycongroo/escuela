package com.alberto.escuela.repositories;

import com.alberto.escuela.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo,Long> {
    boolean existsByMaestro_IdMaestro(Long id);
    boolean existsByAula_IdAula(Long id);
    boolean existsByCurso_IdCurso(Long id);

    boolean existsByCurso_IdCursoAndMaestroIdMaestroAndAula_IdAulaAndPeriodo(Long cursoIdCurso, Long maestroIdMaestro, Long aulaIdAula, String periodo);

    boolean existsByIdGrupoNotAndCurso_IdCursoAndMaestro_IdMaestroAndAula_IdAulaAndPeriodo(Long idGrupo, Long cursoIdCurso, Long maestroIdMaestro, Long aulaIdAula, String periodo);

    List<Grupo> findByAula_IdAulaAndPeriodo(Long aulaIdAula, String periodo);


}
