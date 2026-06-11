package com.alberto.escuela.services.aulas;

import com.alberto.escuela.dto.Aulas.AulaRequest;
import com.alberto.escuela.dto.Aulas.AulaResponse;
import com.alberto.escuela.entities.Aula;
import com.alberto.escuela.mappers.AulaMapper;
import com.alberto.escuela.repositories.AulaRepository;
import com.alberto.escuela.repositories.GrupoRepository;
import com.alberto.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class AulaServiceImpl implements AulaService{
    private final AulaRepository aulaRepository;
    private final AulaMapper aulaMapper;
    private final GrupoRepository grupoRepository;


    @Override
    @Transactional(readOnly = true)
    public List<AulaResponse> listar() {
        return aulaRepository.findAll().stream().map(aulaMapper::entidadResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AulaResponse obtenerPorId(Long id) {
        return aulaMapper.entidadResponse(obtenerAula(id));
    }

    @Override
    public AulaResponse registrar(AulaRequest request) {
        if (aulaRepository.existsByNombreIgnoreCase(request.nombre().trim()))
            throw new IllegalArgumentException("El nombre del aula ya existe");

        Aula aula = aulaMapper.requestEntidad(request);
        aulaRepository.save(aula);

        return aulaMapper.entidadResponse(aula);
    }

    @Override
    public AulaResponse actualizar(AulaRequest request, Long id) {
        Aula aula = obtenerAula(id);

        if (aulaRepository.existsByNombreIgnoreCaseAndIdAulaNot(request.nombre().trim(),id))
            throw new IllegalArgumentException("Ya existe un aula con el nombre "+request.nombre());

        aula.actualizar(request.nombre(), request.capacidad());
        return aulaMapper.entidadResponse(aula);
    }

    @Override
    public void eliminar(Long id) {
        Aula aula = obtenerAula(id);

        if (grupoRepository.existsByAula_IdAula(id))
            throw new IllegalStateException("La aula tiene grupos registrados");

        aulaRepository.delete(aula);
    }

    private Aula obtenerAula(Long id){
        return ServiceUtils.obtenerEntidadOException(aulaRepository,id,Aula.class);
    }
}
