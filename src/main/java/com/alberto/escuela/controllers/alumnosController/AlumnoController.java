package com.alberto.escuela.controllers.alumnosController;

import com.alberto.escuela.controllers.CommonController;
import com.alberto.escuela.dto.Alumnos.AlumnoRequest;
import com.alberto.escuela.dto.Alumnos.AlumnoResponse;
import com.alberto.escuela.services.alumnos.AlumnoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alumnos")
@Validated
public class AlumnoController extends CommonController<AlumnoRequest, AlumnoResponse,AlumnoService> {
    public AlumnoController(AlumnoService service) {
        super(service);
    }
}
