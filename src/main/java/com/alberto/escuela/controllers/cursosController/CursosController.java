package com.alberto.escuela.controllers.cursosController;

import com.alberto.escuela.controllers.CommonController;
import com.alberto.escuela.dto.Cursos.CursoRequest;
import com.alberto.escuela.dto.Cursos.CursoResponse;
import com.alberto.escuela.repositories.CursoRepository;
import com.alberto.escuela.services.cursos.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cursos")
@Validated
public class CursosController extends CommonController<CursoRequest, CursoResponse, CursoService> {
    public CursosController(CursoService service) {
        super(service);
    }
}
