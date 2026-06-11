package com.alberto.escuela.entities;

import com.alberto.escuela.utils.StringCustomUtils;
import com.alberto.escuela.utils.ValoresNumericosUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter @Getter
@Table(name = "GRUPOS",uniqueConstraints = @UniqueConstraint(columnNames = {"ID_CURSO","ID_MAESTRO","ID_AULA","PERIODO"}, name ="GRUPO_CU_MA_AU_PE_UK" ))
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GRUPO")
    private Long idGrupo;

    @Column(name = "PERIODO", nullable = false, length = 20)
    @Size(min = 1, max = 20,message = "El periodo debe tener de 1 a 20 caracteres")
    private String periodo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CURSO", nullable = false)
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MAESTRO",nullable = false)
    private Maestro maestro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AULA",nullable = false)
    private Aula aula;
    @Builder.Default
    @OneToMany(mappedBy = "grupo",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "grupo",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Horario> horarios = new ArrayList<>();

    public void actualizar(String periodo){
        StringCustomUtils.validarTamanio(periodo,1,20,"El periodod debe tener de 1 a 20 caracteres");
        this.periodo = periodo;
    }


}
