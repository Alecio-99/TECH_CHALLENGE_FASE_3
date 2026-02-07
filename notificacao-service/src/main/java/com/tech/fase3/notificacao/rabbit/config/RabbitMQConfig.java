package main.java.com.tech.fase3.notificacao.rabbit.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String CONSULTA_QUEUE = "consulta.notificacao";

    @Bean
    public Queue consultaQueue() {
        return new Queue(CONSULTA_QUEUE);
    }
}
