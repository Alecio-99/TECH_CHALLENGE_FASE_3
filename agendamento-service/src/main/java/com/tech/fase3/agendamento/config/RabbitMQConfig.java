package main.java.com.tech.fase3.agendamento.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String CONSULTA_QUEUE = "consulta.notificacao";

    @Bean
    DirectExchange consultaExchange() {
        return new DirectExchange("consulta.exchange");
    }

}
