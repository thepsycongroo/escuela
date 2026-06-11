package com.alberto.escuela.entities;

import com.alberto.escuela.utils.ValoresNumericosUtils;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
@Table(name = "CALIFICACIONES")
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CALIFICACION")
    private Long idCalificacion;

    @Column(name = "CALIFICACION",nullable = false)
    private BigDecimal calificacion;

    @Column(name = "FECHA_REGISTRO",nullable = false)
    private LocalDate fechaRegistro;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INSCRIPCION", nullable = false, unique = true)
    private Inscripcion inscripcion;

    public void actualizar(BigDecimal calificacion, LocalDate fechaRegistro ){
        ValoresNumericosUtils.validarBigDecimalPositivo(calificacion,"La calififcacion es requerida y debe ser positiva");

        this.calificacion = calificacion;
        this.fechaRegistro = fechaRegistro;
    }



}
