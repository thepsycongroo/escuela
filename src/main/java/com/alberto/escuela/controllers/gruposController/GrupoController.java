package com.alberto.escuela.controllers.gruposController;

import com.alberto.escuela.controllers.CommonController;
import com.alberto.escuela.dto.Grupos.GrupoRequest;
import com.alberto.escuela.dto.Grupos.GrupoResponse;
import com.alberto.escuela.entities.Grupo;
import com.alberto.escuela.services.GruposService.GrupoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grupos")
@Validated
public class GrupoController extends CommonController<GrupoRequest, GrupoResponse, GrupoService> {
    public GrupoController(GrupoService service) {
        super(service);
    }
}
