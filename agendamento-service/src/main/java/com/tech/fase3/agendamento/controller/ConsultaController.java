package com.tech.fase3.agendamento.controller;

import com.tech.fase3.agendamento.domain.StatusConsulta;
import com.tech.fase3.agendamento.dto.ConsultaDTO;
import com.tech.fase3.agendamento.service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('MEDICO','ENFERMEIRO')")
    public ResponseEntity<Void> criar(@RequestBody ConsultaDTO dto) {
        service.criar(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MEDICO')")
    public ResponseEntity<Void> editar(
            @PathVariable Long id,
            @RequestParam StatusConsulta status
    ) {
        service.editar(id, status);
        return ResponseEntity.ok().build();
    }
}
