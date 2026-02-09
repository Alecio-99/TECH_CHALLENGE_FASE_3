package com.tech.fase3.agendamento.service;

import com.tech.fase3.agendamento.domain.*;
import com.tech.fase3.agendamento.dto.ConsultaDTO;
import com.tech.fase3.agendamento.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository repository;
    private final ConsultaProducer producer;

    public ConsultaService(ConsultaRepository repository, ConsultaProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    public Consulta criar(ConsultaDTO dto) {
        Consulta c = new Consulta();
        c.setMedicoId(dto.medicoId());
        c.setPacienteId(dto.pacienteId());
        c.setEnfermeiroId(dto.enfermeiroId());
        c.setDataHora(LocalDateTime.parse(dto.dataHora()));
        c.setStatus(StatusConsulta.AGENDADA);
        c.setObservacoes(dto.observacoes());

        Consulta salva = repository.save(c);
        producer.consultaCriada(salva.getId());
        return salva;
    }

    public Consulta editar(Long id, StatusConsulta status) {
        Consulta c = repository.findById(id).orElseThrow();
        c.setStatus(status);
        Consulta salva = repository.save(c);
        producer.consultaEditada(salva.getId());
        return salva;
    }

    public List<Consulta> listarTodas() {
        return repository.findAll();
    }

    public List<Consulta> listarPorPaciente(Long pacienteId) {
        return repository.findByPacienteId(pacienteId);
    }

    public List<Consulta> listarFuturas(Long pacienteId) {
        return repository.findByPacienteIdAndDataHoraAfter(
                pacienteId, LocalDateTime.now()
        );
    }
}
