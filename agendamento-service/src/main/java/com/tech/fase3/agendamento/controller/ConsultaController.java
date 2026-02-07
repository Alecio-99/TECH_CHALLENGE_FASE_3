package main.java.com.tech.fase3.agendamento.controller;


import main.java.com.tech.fase3.agendamento.services.ConsultaProducer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaProducer consultaProducer;

    public ConsultaController(ConsultaProducer consultaProducer) {
        this.consultaProducer = consultaProducer;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO')")
    public String criarConsulta(Authentication auth) {
        consultaProducer.enviarConsultaCriada(auth.getName());
        return "Consulta criada";
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MEDICO')")
    public String editarConsulta(@PathVariable String id) {
        consultaProducer.enviarConsultaEditada(id);
        return "Consulta " + id + " editada";
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO')")
    public String listarConsultas() {
        return "Listando histórico de consultas";
    }

    @GetMapping("/meu")
    @PreAuthorize("hasRole('PACIENTE')")
    public String minhasConsultas(Authentication auth) {
        return "Consultas do paciente " + auth.getName();
    }

}
