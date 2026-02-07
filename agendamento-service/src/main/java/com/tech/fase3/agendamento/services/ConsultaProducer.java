package main.java.com.tech.fase3.agendamento.services;


import main.java.com.tech.fase3.agendamento.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsultaProducer {

    private final RabbitTemplate rabbitTemplate;

    public ConsultaProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarConsultaCriada(String paciente) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.CONSULTA_QUEUE,
                "Consulta criada para paciente " + paciente
        );
    }

    public void enviarConsultaEditada(String consultaId) {
        String mensagem = "CONSULTA_EDITADA | Consulta ID: " + consultaId;
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.CONSULTA_QUEUE,
                mensagem
        );
    }
}
