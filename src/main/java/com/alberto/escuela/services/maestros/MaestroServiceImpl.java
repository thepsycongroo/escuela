package com.alberto.escuela.services.maestros;

import com.alberto.escuela.dto.Maestros.MaestroRequest;
import com.alberto.escuela.dto.Maestros.MaestroResponse;
import com.alberto.escuela.entities.Maestro;
import com.alberto.escuela.mappers.MaestroMapper;
import com.alberto.escuela.repositories.GrupoRepository;
import com.alberto.escuela.repositories.MaestroRepository;
import com.alberto.escuela.utils.ServiceUtils;
import com.alberto.escuela.utils.StringCustomUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class MaestroServiceImpl implements MaestroService{
    private final MaestroRepository maestroRepository;
    private final MaestroMapper maestroMapper;
    private final GrupoRepository grupoRepository;
    @Override
    @Transactional(readOnly = true)
    public List<MaestroResponse> listar() {
        log.info("Listando todos los maestros solicitados");
        return maestroRepository.findAll().stream().map(entidad->maestroMapper.entidadResponse(entidad)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MaestroResponse obtenerPorId(Long id) {
        return maestroMapper.entidadResponse(obtenerMaestro(id));
    }

    @Override
    public MaestroResponse registrar(MaestroRequest request) {
        this.validaDatosUnicos(request);

        Maestro maestro = maestroMapper.requestEntidad(request);
        maestroRepository.save(maestro);
        log.info("Nuevo maestro {} registrado", maestro.getNombre());
        return maestroMapper.entidadResponse(maestro);
    }

    @Override
    public MaestroResponse actualizar(MaestroRequest request, Long id) {
        Maestro maestro = obtenerMaestro(id);
        validaCambiosDatosUnicos(request,id);
        maestro.actualizar(request.nombre(),request.apellidoPaterno(),
                request.apellidoMaterno(),
                request.email(),
                request.telefono());

        maestroRepository.save(maestro);

        log.info("Maestro {} actualizado correctamente", maestro.getNombre());
        return maestroMapper.entidadResponse(maestro);
    }

    @Override
    public void eliminar(Long id) {
        Maestro maestro = obtenerMaestro(id);
        log.info("Eliminado Maestro con id  {}",id);
        if (grupoRepository.existsByMaestro_IdMaestro(id))
            throw new IllegalStateException("El maestro tiene almenos un grupo asignado");
        maestroRepository.delete(maestro);
        log.info("Maestro con id {} eliminado",id);
    }

    private Maestro obtenerMaestro(Long id){
        return ServiceUtils.obtenerEntidadOException(maestroRepository,id,Maestro.class);
    }


    private void validaDatosUnicos(MaestroRequest request){
        log.info("validando email unico..");
        if (maestroRepository.existsByEmailIgnoreCase(request.email().trim()))
            throw new IllegalArgumentException("Ya existe un maestro registrado con el email: "+ request.email());
        log.info("validando telefono unico..");
        if (maestroRepository.existsByTelefono(request.telefono().trim()))
            throw  new IllegalArgumentException("Ya existe un maestro registrado con el telefono: "+request.telefono());

    }

    private void validaCambiosDatosUnicos(MaestroRequest request,Long id){
        log.info("validando email unico..");
        if (maestroRepository.existsByEmailIgnoreCaseAndIdMaestroNot(request.email().trim(),id))
            throw new IllegalArgumentException("Ya existe un maestro registrado con el email: "+ request.email());
        log.info("validando telefono unico..");
        if (maestroRepository.existsByTelefonoAndIdMaestroNot(request.telefono().trim(),id))
            throw  new IllegalArgumentException("Ya existe un maestro registrado con el telefono: "+request.telefono());

    }
}
