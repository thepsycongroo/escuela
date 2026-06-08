package com.alberto.escuela.entities;

import com.alberto.escuela.utils.StringCustomUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Builder
@Table(name = "ALUMNOS")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_ALUMNO")
    private Long idAlumno;

    @Column(name="NOMBRE", nullable = false,length = 50)
    @Size(min = 4, max =50, message = "El nombre debe tener de 4 a 50 caracteres")
    private String nombre;
    @Column(name="APELLIDO_PATERNO", nullable = false, length = 50)
    @Size(min = 5, max =50, message = "El apellido paterno debe tener de 5 a 50 caracteres")
    private String apellidoPaterno;
    @Column(name="APELLIDO_MATERNO", nullable = false, length = 50)
    @Size(min = 5, max =50, message = "El apellido materno debe tener de 5 a 50 caracteres")
    private String apellidoMaterno;
    @Column(name="EMAIL", nullable = false, length = 100)
    @Size(min = 1, max =100, message = "El email debe tener de 1 a 100 caracteres")
    @Email(message = "El email es invalido")
    private String email;
    @Column(name="MATRICULA", nullable = false, length = 10)
    @Size(min = 10, max =10, message = "La matricula debe tener 10 caracteres")
    private String matricula;
    @Builder.Default
    @Column(name="FECHA_INGRESO")
    private LocalDate fechaIngreso = LocalDate.now();

    @Builder.Default
    @OneToMany(mappedBy = "alumno",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones = new ArrayList<>();

    public void actualizar(String nombre,String apellidoPaterno,String apellidoMaterno,String email,String matricula,LocalDate fechaIngreso){
        StringCustomUtils.validarTamanio(nombre,4,50,"El nombre es requerido y debe tener de 4 a 50 caracteres");
        StringCustomUtils.validarTamanio(apellidoPaterno,5,50,"El apellido paterno es requerido y debe tener de 5 a 50 caracteres");
        StringCustomUtils.validarTamanio(apellidoMaterno,5,50,"El apellido materno es requerido y debe tener de 5 a 50 caracteres");
        StringCustomUtils.validarTamanio(email,1,100,"El email es requerido y debe tener de 1 a 100 caracteres");
        StringCustomUtils.validarTamanio(matricula,10,10,"El matricula es requerido y debe tener 10 caracteres");
        ;


        this.nombre = nombre.trim();
        this.apellidoPaterno = apellidoPaterno.trim();
        this.apellidoMaterno = apellidoMaterno.trim();
        this.email = email.trim();
        this.matricula = matricula.trim();
        this.fechaIngreso = fechaIngreso;
    }


}
