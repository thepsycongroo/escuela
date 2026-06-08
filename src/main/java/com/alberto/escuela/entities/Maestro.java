package com.alberto.escuela.entities;

import com.alberto.escuela.utils.StringCustomUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@Builder
@Table(name = "MAESTROS")
public class Maestro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAESTRO")
    private Long idMaestro;

    @Column(name = "NOMBRE", nullable = false,length = 50)
    @Size(min = 4, max = 50, message = "El nombre debe tener de 4 a 50 caracteres")
    private String nombre;

    @Column(name = "APELLIDO_PATERNO", nullable = false, length = 50)
    @Size(min = 5, max = 50, message = "El apellido paterno debe tener de 5 a 50 caracteres")
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO", nullable = false, length = 50)
    @Size(min = 5, max = 50, message = "El apellido materno debe tener de 5 a 50 caracteres")
    private String apellidoMaterno;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    @Size(min = 1, max = 100, message = "El email debe tener de 1 a 100 caracteres")
    @Email(message = "El email es invalido")
    private String email;

    @Column(name = "TELEFONO", nullable = false, unique = true, length = 10)
    @Size(min = 10, max = 10, message = "El telefono debe tener de 10 numeros")
    private String telefono;

    //foraneas
    @Builder.Default
    @OneToMany(mappedBy = "maestro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Grupo> grupos = new ArrayList<>();


    public void actualizar(String nombre,String apellidoPaterno, String apellidoMaterno, String email, String telefono){
        StringCustomUtils.validarTamanio(nombre,4,50, "El nombre es requerido y debe tener de 4 a 50 caracteres");
        StringCustomUtils.validarTamanio(apellidoPaterno,5,50, "El apellido paterno es requerido y debe tener de 5 a 50 caracteres");
        StringCustomUtils.validarTamanio(apellidoMaterno,5,50, "El apellido materno es requerido y debe tener de 5 a 50 caracteres");
        StringCustomUtils.validarTamanio(email,1,100, "El email es requerido y debe tener de 1 a 100 caracteres");
        StringCustomUtils.validarTamanio(telefono,10,10, "El telefono es requerido y debe tener de 10 caracteres");

        this.nombre = nombre.trim();
        this.apellidoPaterno = apellidoPaterno.trim();
        this.apellidoMaterno= apellidoMaterno.trim();
        this.email=email.trim();
        this.telefono = telefono.trim();


    }


}
