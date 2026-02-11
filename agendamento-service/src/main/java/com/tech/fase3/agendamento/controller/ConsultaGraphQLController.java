package com.tech.fase3.agendamento.controller;

import com.tech.fase3.agendamento.domain.Consulta;
import com.tech.fase3.agendamento.service.ConsultaService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;

@Controller
public class ConsultaGraphQLController {

    private static final long PACIENTE_ID_PADRAO = 1L;

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

    @QueryMapping
    @PreAuthorize("hasAnyRole('MEDICO','ENFERMEIRO','PACIENTE')")
    public List<Consulta> atendimentosPorPaciente(
            @Argument Long pacienteId,
            Authentication auth) {
        Long id = pacienteIdParaUsuario(pacienteId, auth);
        return id != null ? service.listarPorPaciente(id) : Collections.emptyList();
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('MEDICO','ENFERMEIRO','PACIENTE')")
    public List<Consulta> atendimentosFuturosPorPaciente(
            @Argument Long pacienteId,
            Authentication auth) {
        Long id = pacienteIdParaUsuario(pacienteId, auth);
        return id != null ? service.listarFuturas(id) : Collections.emptyList();
    }

    /**
     * Para PACIENTE: só permite consultar o próprio histórico (pacienteId deve coincidir).
     * Para MEDICO/ENFERMEIRO: permite qualquer pacienteId.
     */
    private Long pacienteIdParaUsuario(Long pacienteId, Authentication auth) {
        if (pacienteId == null) return null;
        boolean isPaciente = auth.getAuthorities().stream()
                .anyMatch(a -> "ROLE_PACIENTE".equals(a.getAuthority()));
        if (isPaciente && !pacienteId.equals(PACIENTE_ID_PADRAO)) {
            return null; // paciente só pode ver o próprio (id 1 no cenário atual)
        }
        return pacienteId;
    }
}
