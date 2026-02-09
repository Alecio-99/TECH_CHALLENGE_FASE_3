package com.tech.fase3.agendamento.service;

import com.tech.fase3.agendamento.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsultaProducer {

    private final RabbitTemplate rabbitTemplate;

    public ConsultaProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void consultaCriada(Long consultaId) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.CONSULTA_EXCHANGE,
                RabbitMQConfig.ROUTING_KEY_CRIADA,
                "CONSULTA_CRIADA|" + consultaId
        );
    }

    public void consultaEditada(Long consultaId) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.CONSULTA_EXCHANGE,
                RabbitMQConfig.ROUTING_KEY_EDITADA,
                "CONSULTA_EDITADA|" + consultaId
        );
    }
}
