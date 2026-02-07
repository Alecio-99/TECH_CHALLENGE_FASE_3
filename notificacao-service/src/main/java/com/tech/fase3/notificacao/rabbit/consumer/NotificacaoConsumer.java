package main.java.com.tech.fase3.notificacao.rabbit.consumer;


import main.java.com.tech.fase3.notificacao.rabbit.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoConsumer {

    @RabbitListener(queues = RabbitMQConfig.CONSULTA_QUEUE)
    public void receberMensagem(String mensagem) {
        System.out.println("📩 Notificação recebida: " + mensagem);
    }
}
