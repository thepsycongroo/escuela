package com.alberto.escuela.controllers.aulasController;

import com.alberto.escuela.controllers.CommonController;
import com.alberto.escuela.dto.Aulas.AulaRequest;
import com.alberto.escuela.dto.Aulas.AulaResponse;
import com.alberto.escuela.services.aulas.AulaService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aulas")
@Validated
public class AulaController extends CommonController<AulaRequest, AulaResponse, AulaService> {

    public AulaController(AulaService service) {
        super(service);
    }
}
