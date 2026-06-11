package com.alberto.escuela.controllers.maestrosController;

import com.alberto.escuela.controllers.CommonController;
import com.alberto.escuela.dto.Maestros.MaestroRequest;
import com.alberto.escuela.dto.Maestros.MaestroResponse;
import com.alberto.escuela.services.maestros.MaestroService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/maestros")
@Validated
public class MaestroController extends CommonController<MaestroRequest,MaestroResponse,MaestroService>{


    public MaestroController(MaestroService service) {
        super(service);
    }



}
