package com.alberto.escuela.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
@Table(name = "INSCRIPCIONES",uniqueConstraints = @UniqueConstraint(name = "INSCRIPCION_ALU_GRU_UK",columnNames = {"ID_ALUMNO","ID_GRUPO"}))
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INSCRIPCION")
    private Long idInscripcion;

    @Column(name = "FECHA_INSCRIPCION")
    private LocalDate fechaInscripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GRUPO")
    private Grupo grupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ALUMNO",nullable = false)
    private Alumno alumno;

    @OneToOne(mappedBy = "inscripcion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Calificacion calificacion;

}
