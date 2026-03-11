package com.tech.fase3.notificacao.rabbit.consumer;

import com.tech.fase3.notificacao.rabbit.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificacaoConsumer {

    @RabbitListener(queues = RabbitMQConfig.CONSULTA_QUEUE)
    public void receberMensagem(String mensagem) {

        if (mensagem.startsWith("CONSULTA_CRIADA")) {
            log.info("Enviando lembrete ao paciente: nova consulta criada -> " + mensagem);
        } else if (mensagem.startsWith("CONSULTA_EDITADA")) {
            log.info("Enviando lembrete ao paciente: consulta alterada -> " + mensagem);
        } else {
            log.info("Notificação recebida: " + mensagem);
        }
    }
}
