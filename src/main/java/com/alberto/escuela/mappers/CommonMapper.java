package com.alberto.escuela.mappers;

import org.springframework.stereotype.Component;

public interface CommonMapper<RQ, RS, E>{

    E requestEntidad(RQ request);
    RS entidadResponse(E entidad);


}
