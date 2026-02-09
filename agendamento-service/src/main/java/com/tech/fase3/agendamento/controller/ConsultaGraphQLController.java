package com.tech.fase3.agendamento.controller;

import com.tech.fase3.agendamento.domain.Consulta;
import com.tech.fase3.agendamento.dto.ConsultaDTO;
import com.tech.fase3.agendamento.service.ConsultaService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ConsultaGraphQLController {

    private final ConsultaService service;

    public ConsultaGraphQLController(ConsultaService service) {
        this.service = service;
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('MEDICO','ENFERMEIRO')")
    public List<Consulta> consultas() {
        return service.listarTodas();
    }

    @QueryMapping
    @PreAuthorize("hasRole('PACIENTE')")
    public List<Consulta> consultasPorPaciente(Authentication auth) {
        return service.listarPorPaciente(1L);
    }

    @QueryMapping
    @PreAuthorize("hasRole('PACIENTE')")
    public List<Consulta> consultasFuturas(Authentication auth) {
        return service.listarFuturas(1L);
    }
}
