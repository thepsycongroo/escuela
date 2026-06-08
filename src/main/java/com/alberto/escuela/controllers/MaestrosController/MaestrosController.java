package com.alberto.escuela.controllers.MaestrosController;

import com.alberto.escuela.dto.Maestros.MaestrosResponse;
import com.alberto.escuela.services.MaestrosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/maestros")
@AllArgsConstructor
@Validated
public class MaestrosController {

        private MaestrosService maestrosService;

    @GetMapping
public ResponseEntity<List<MaestrosResponse>> listar(){
        return ResponseEntity.ok(maestrosService.listar());
    }






}
