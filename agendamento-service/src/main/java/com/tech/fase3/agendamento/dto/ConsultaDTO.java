package com.tech.fase3.agendamento.dto;

public record ConsultaDTO(
        Long id,
        Long medicoId,
        Long pacienteId,
        Long enfermeiroId,
        String dataHora,
        String status,
        String observacoes
) {}
