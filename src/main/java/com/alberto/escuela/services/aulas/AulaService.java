package com.alberto.escuela.services.aulas;

import com.alberto.escuela.dto.Aulas.AulaRequest;
import com.alberto.escuela.dto.Aulas.AulaResponse;
import com.alberto.escuela.services.CrudService;
import org.springframework.stereotype.Service;

public interface AulaService extends CrudService<AulaRequest, AulaResponse> {
}
