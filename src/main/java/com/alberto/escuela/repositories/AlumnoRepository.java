package com.alberto.escuela.repositories;

import com.alberto.escuela.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno,Long> {


    @Query("SELECT GENERAR_MATRICULA(:nombre,:apellidoPaterno,:apellidoMaterno)")
    String obtenerMatricula(@Param("nombre") String nombre,
                            @Param("apellidoPaterno") String apellidoPaterno,
                            @Param("apellidoMaterno") String apellidoMaterno);

    @Query("SELECT GENERAR_CORREO(:nombre,:apellidoPaterno,:apellidoMaterno)")
    String obtenerCorreo(@Param("nombre") String nombre,
                            @Param("apellidoPaterno") String apellidoPaterno,
                            @Param("apellidoMaterno") String apellidoMaterno);

}
