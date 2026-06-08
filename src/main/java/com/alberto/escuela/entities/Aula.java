package com.alberto.escuela.entities;

import com.alberto.escuela.utils.StringCustomUtils;
import com.alberto.escuela.utils.ValoresNumericosUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@Table(name = "AULAS")
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AULA")
    private Long idAula;

    @Column(name = "NOMBRE", nullable = false, length = 30)
    @Size(min = 4, max = 30, message = "El nombre debe tener de 4 a 30 caracteres")
    private String nombre;

    @Column(name = "CAPACIDAD", nullable = false, length = 4)
    private Integer capacidad;

    @Builder.Default
    @OneToMany(mappedBy = "aula", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Grupo> grupos = new ArrayList<>();

    public void actualizar(String nombre,Integer capacidad){
        StringCustomUtils.validarTamanio(nombre,4,30,"El nombre es requerido y debe tener de 4 a 30 caracteres");
        ValoresNumericosUtils.validarEnteroPositivo(capacidad,"La capacidad debe ser positiva");

        this.nombre = nombre;
        this.capacidad = capacidad;
        
    }


}
