package com.tech.fase3.notificacao.rabbit.consumer;

import com.tech.fase3.notificacao.rabbit.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoConsumer {

    @RabbitListener(queues = RabbitMQConfig.CONSULTA_QUEUE)
    public void receberMensagem(String mensagem) {

        if (mensagem.startsWith("CONSULTA_CRIADA")) {
            System.out.println("Lembrete: nova consulta criada -> " + mensagem);
        } else if (mensagem.startsWith("CONSULTA_EDITADA")) {
            System.out.println("Lembrete: consulta alterada -> " + mensagem);
        } else {
            System.out.println("Notificação recebida: " + mensagem);
        }
    }
}
