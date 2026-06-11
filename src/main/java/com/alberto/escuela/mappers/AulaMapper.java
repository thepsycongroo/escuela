package com.alberto.escuela.mappers;

import com.alberto.escuela.dto.Aulas.AulaRequest;
import com.alberto.escuela.dto.Aulas.AulaResponse;
import com.alberto.escuela.dto.datos.DatosAula;
import com.alberto.escuela.entities.Aula;
import com.alberto.escuela.repositories.AulaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AulaMapper implements CommonMapper<AulaRequest, AulaResponse, Aula>{
    @Override
    public Aula requestEntidad(AulaRequest request) {
        if (request==null)
        return null;
        return Aula.builder().nombre(request.nombre()).capacidad(request.capacidad()).build();
    }

    @Override
    public AulaResponse entidadResponse(Aula entidad) {
        return new AulaResponse(entidad.getIdAula(),entidad.getNombre(),entidad.getCapacidad());
    }

    public DatosAula entidadADatosAula(Aula entidad){
        return new DatosAula(entidad.getNombre(),entidad.getCapacidad());
    }
}
