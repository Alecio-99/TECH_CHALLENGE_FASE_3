package com.tech.fase3.agendamento.repository;

import com.tech.fase3.agendamento.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByPacienteId(Long pacienteId);

    List<Consulta> findByPacienteIdAndDataHoraAfter(Long pacienteId, LocalDateTime dataHora);
}
