package com.alberto.escuela.services;

import com.alberto.escuela.mappers.CommonMapper;
import com.alberto.escuela.repositories.MaestrosRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class MaestrosService implements CrudService {

    private final MaestrosRepository maestrosRepository;
    private final CommonMapper commonMapper;
    @Override
    public List listar() {
        return maestrosRepository.findAll().stream().toList();
    }

    @Override
    public Object obtenerPorId(Long id) {
        return null;
    }

    @Override
    public Object registrar(Object request) {
        return null;
    }

    @Override
    public Object actualizar(Object request, Long id) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }

}
