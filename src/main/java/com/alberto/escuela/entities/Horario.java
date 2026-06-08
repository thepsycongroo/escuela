package com.alberto.escuela.entities;

import com.alberto.escuela.emuns.DiaSemana;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
@Table(name = "HORARIOS")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HORARIO", nullable = false)
    private Long idHorario;
    @Column(name = "DIA", nullable = false)
    @Enumerated(EnumType.STRING)
    private DiaSemana dia;
    @Column(name = "HORA_INICIO", nullable = false)
    private String horaInicio;
    @Column(name = "HORA_FIN", nullable = false)
    private String horaFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GRUPO", nullable = false)
    private Grupo grupo;



}
