package com.alberto.escuela.controllers.inscripcionesController;

import com.alberto.escuela.controllers.CommonController;
import com.alberto.escuela.dto.Inscripciones.InscripcionRequest;
import com.alberto.escuela.dto.Inscripciones.InscripcionResponse;
import com.alberto.escuela.services.inscripciones.InscripcionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController extends CommonController<InscripcionRequest, InscripcionResponse, InscripcionService> {
    public InscripcionController(InscripcionService service) {
        super(service);
    }
}
