package main.java.com.tech.fase3.agendamento.controller;

import main.java.com.tech.fase3.agendamento.dto.ConsultaDTO;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ConsultaGraphQLController {

    @QueryMapping
    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO')")
    public List<ConsultaDTO> consultas() {
        return List.of(
                new ConsultaDTO(
                        1L, 10L, 100L, 20L,
                        "2026-02-05T10:00",
                        "REALIZADA",
                        "Consulta de rotina"
                ),
                new ConsultaDTO(
                        2L, 11L, 101L, 21L,
                        "2026-02-10T14:00",
                        "AGENDADA",
                        "Retorno"
                )
        );
    }

    @QueryMapping
    @PreAuthorize("hasRole('PACIENTE')")
    public List<ConsultaDTO> consultasPorPaciente(Authentication auth) {
        return List.of(
                new ConsultaDTO(
                        3L, 12L, null, null,
                        "2026-02-01T09:00",
                        "REALIZADA",
                        "Consulta do paciente " + auth.getName()
                )
        );
    }

    @QueryMapping
    @PreAuthorize("hasRole('PACIENTE')")
    public List<ConsultaDTO> consultasFuturas(Authentication auth) {
        return List.of(
                new ConsultaDTO(
                        4L, 15L, null, null,
                        "2026-02-20T10:00",
                        "AGENDADA",
                        "Consulta futura do paciente " + auth.getName()
                )
        );
    }


}
