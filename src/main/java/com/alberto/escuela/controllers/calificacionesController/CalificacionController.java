package com.alberto.escuela.controllers.calificacionesController;

import com.alberto.escuela.controllers.CommonController;
import com.alberto.escuela.dto.Calificaciones.CalificacionRequest;
import com.alberto.escuela.dto.Calificaciones.CalificacionResponse;
import com.alberto.escuela.services.calificaciones.CalificacionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calificaciones")
@Validated
public class CalificacionController extends CommonController<CalificacionRequest, CalificacionResponse, CalificacionService> {
    public CalificacionController(CalificacionService service) {
        super(service);
    }
}
