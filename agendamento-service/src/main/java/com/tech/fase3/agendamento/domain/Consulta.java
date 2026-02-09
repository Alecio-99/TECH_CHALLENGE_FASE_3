package com.tech.fase3.agendamento.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long medicoId;
    private Long pacienteId;
    private Long enfermeiroId;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private StatusConsulta status;

    private String observacoes;

    public Long getId() { return id; }
    public Long getMedicoId() { return medicoId; }
    public Long getPacienteId() { return pacienteId; }
    public Long getEnfermeiroId() { return enfermeiroId; }
    public LocalDateTime getDataHora() { return dataHora; }
    public StatusConsulta getStatus() { return status; }
    public String getObservacoes() { return observacoes; }

    public void setMedicoId(Long medicoId) { this.medicoId = medicoId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public void setEnfermeiroId(Long enfermeiroId) { this.enfermeiroId = enfermeiroId; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public void setStatus(StatusConsulta status) { this.status = status; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
