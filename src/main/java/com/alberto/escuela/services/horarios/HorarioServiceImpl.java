package com.alberto.escuela.services.horarios;

import com.alberto.escuela.dto.Horarios.HorarioRequest;
import com.alberto.escuela.dto.Horarios.HorarioResponse;
import com.alberto.escuela.dto.Maestros.MaestroRequest;
import com.alberto.escuela.dto.Maestros.MaestroResponse;
import com.alberto.escuela.entities.Grupo;
import com.alberto.escuela.entities.Horario;
import com.alberto.escuela.exceptions.RecursoNoEncontradoException;
import com.alberto.escuela.mappers.HorariosMapper;
import com.alberto.escuela.repositories.GrupoRepository;
import com.alberto.escuela.repositories.HorarioRepository;
import com.alberto.escuela.utils.ServiceUtils;
import com.alberto.escuela.utils.StringCustomUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class HorarioServiceImpl implements HorarioService {
    private final HorarioRepository horarioRepository;
    private final HorariosMapper horariosMapper;
    private final GrupoRepository grupoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<HorarioResponse> listar() {
        return horarioRepository.findAll().stream().map(horariosMapper::entidadResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public HorarioResponse obtenerPorId(Long id) {
        return horariosMapper.entidadResponse(obtenerHorario(id));
    }

    @Override
    public HorarioResponse registrar(HorarioRequest request) {
        Grupo grupo = grupoRepository.findById(request.idGrupo())
                .orElseThrow(() -> new RecursoNoEncontradoException("El id grupo es inválido"));

        StringCustomUtils.validaHoraInicioFin(
                request.horaInicio(),
                request.horaFin()
        );

        grupoRepository.findByAula_IdAulaAndPeriodo(
                        grupo.getAula().getIdAula(),
                        grupo.getPeriodo().trim()
                ).stream()
                .flatMap(grupoExistente -> grupoExistente.getHorarios().stream())
                .filter(horario -> horario.getDia().equals(request.dia()))
                .forEach(horario -> StringCustomUtils.validaHoraSuperpuestas(
                        request.horaInicio(),
                        request.horaFin(),
                        horario.getHoraInicio(),
                        horario.getHoraFin()
                ));

        Horario horario = horariosMapper.requestEntidad(request);
        horario.setGrupo(grupo);
        horarioRepository.save(horario);

        return horariosMapper.entidadResponse(horario);

    }

    @Override
    public HorarioResponse actualizar(HorarioRequest request, Long id) {
        Grupo grupo = grupoRepository.findById(request.idGrupo())
                .orElseThrow(() -> new RecursoNoEncontradoException("El id grupo es inválido"));
        Horario horario =obtenerHorario(id);

        StringCustomUtils.validaHoraInicioFin(
                request.horaInicio(),
                request.horaFin()
        );

        grupoRepository.findByAula_IdAulaAndPeriodo(
                        grupo.getAula().getIdAula(),
                        grupo.getPeriodo().trim()
                ).stream()
                .flatMap(grupoExistente -> grupoExistente.getHorarios().stream())
                .filter(horarioAct -> horarioAct.getDia().equals(request.dia()))
                .filter(horarioAct -> horarioAct.getIdHorario().compareTo(id) != 0)
                .forEach(horarioAct -> StringCustomUtils.validaHoraSuperpuestas(
                        request.horaInicio(),
                        request.horaFin(),
                        horarioAct.getHoraInicio(),
                        horarioAct.getHoraFin()
                ));

        horario.actualizar(request.dia(), request.horaInicio(), request.horaFin());
        horario.setGrupo(grupo);
        return horariosMapper.entidadResponse(horario);
    }

    @Override
    public void eliminar(Long id) {
        Horario horario = obtenerHorario(id);
        horarioRepository.delete(horario);
    }

    private Horario obtenerHorario(Long id){
        return ServiceUtils.obtenerEntidadOException(horarioRepository,id,Horario.class);
    }

}
