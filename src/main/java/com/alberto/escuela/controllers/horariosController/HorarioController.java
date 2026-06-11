package com.alberto.escuela.controllers.horariosController;

import com.alberto.escuela.controllers.CommonController;
import com.alberto.escuela.dto.Horarios.HorarioRequest;
import com.alberto.escuela.dto.Horarios.HorarioResponse;
import com.alberto.escuela.services.horarios.HorarioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController extends CommonController<HorarioRequest, HorarioResponse, HorarioService> {
    public HorarioController(HorarioService service) {
        super(service);
    }
}
