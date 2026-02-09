package com.tech.fase3.agendamento.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String CONSULTA_QUEUE = "consulta.notificacao";
    public static final String CONSULTA_EXCHANGE = "consulta.exchange";

    public static final String ROUTING_KEY_CRIADA = "consulta.criada";
    public static final String ROUTING_KEY_EDITADA = "consulta.editada";

    @Bean
    public Queue consultaQueue() {
        return new Queue(CONSULTA_QUEUE, true);
    }

    @Bean
    public DirectExchange consultaExchange() {
        return new DirectExchange(CONSULTA_EXCHANGE);
    }

    @Bean
    public Binding criadaBinding() {
        return BindingBuilder.bind(consultaQueue())
                .to(consultaExchange())
                .with(ROUTING_KEY_CRIADA);
    }

    @Bean
    public Binding editadaBinding() {
        return BindingBuilder.bind(consultaQueue())
                .to(consultaExchange())
                .with(ROUTING_KEY_EDITADA);
    }
}
