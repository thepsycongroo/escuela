package com.alberto.escuela.mappers;

import com.alberto.escuela.dto.Horarios.HorarioRequest;
import com.alberto.escuela.dto.Horarios.HorarioResponse;
import com.alberto.escuela.dto.datos.DatosGrupo;
import com.alberto.escuela.entities.Grupo;
import com.alberto.escuela.entities.Horario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class HorariosMapper implements CommonMapper<HorarioRequest, HorarioResponse, Horario> {


    @Override
    public Horario requestEntidad(HorarioRequest request) {
        if (request ==null)
        return null;

        return Horario.builder()
                .grupo(Grupo.builder().idGrupo(request.idGrupo()).build())
                .dia(request.dia())
                .horaInicio(request.horaInicio())
                .horaFin(request.horaFin())
                .build();
    }

    @Override
    public HorarioResponse entidadResponse(Horario entidad) {

        if (entidad==null) return null;
        return new HorarioResponse(entidad.getIdHorario(),entidadADatosGrupo(entidad),entidadADatosHorario(entidad));
    }


    public String entidadADatosHorario(Horario entidad){
        String horario = String.format(
                "%s %s - %s",
                entidad.getDia(),
                entidad.getHoraInicio(),
                entidad.getHoraFin()
        );
        return horario;
    }

    private DatosGrupo entidadADatosGrupo(Horario entidad){
        if (entidad == null || entidad.getGrupo() == null) {
            return null;
        }

        Grupo grupo = entidad.getGrupo();

        return new DatosGrupo(
                grupo.getCurso().getNombre(),
                String.join(" ",grupo.getMaestro().getNombre(),grupo.getMaestro().getApellidoPaterno(),grupo.getMaestro().getApellidoMaterno()),
                grupo.getAula().getNombre(),
                grupo.getPeriodo()
        );
    }

    /*private DatosGrupo entidadADatosGrupo(Horario entidad){
        if (entidad==null) return null;

        return grupoMapper.entidadADatosGrupo(entidad.getGrupo());
    }*/
}
