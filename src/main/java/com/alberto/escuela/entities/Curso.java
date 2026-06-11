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
@Table(name = "CURSOS")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CURSO")
    private Long idCurso;
    @Column(name = "NOMBRE", nullable = false, length = 100, unique = true)
    //@Size(min = 4,max = 50,message = "El nombre debe tener de 4 a 50 caracteres")
    private String nombre;
    @Column(name = "DESCRIPCION", length = 200)
    //@Size(max = 200,message = "la descripcion debe tener maximo 200 caracteres")
    private String descripcion;
    @Column(name = "CREDITOS", nullable = false)
    private Integer creditos;

    @Builder.Default
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Grupo> grupos = new ArrayList<>();

    public void actualizar(String nombre,String descripcion,Integer creditos){
        StringCustomUtils.validarTamanio(nombre,4,50,"El nombre es obligatorio y debe tener de 4 a 50 caracteres");
        ValoresNumericosUtils.validarEnteroPositivo(creditos,"El numero debe se positivo");

        this.nombre = nombre.trim();
        this.descripcion = descripcion.trim();
        this.creditos = creditos;

    }

}
