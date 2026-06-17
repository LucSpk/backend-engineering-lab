package com.lucas.alves;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private static final String TOPIC = "mensagens";

    @KafkaListener(topics = TOPIC)
    public void read(String mensagem) {
        try {
            System.out.println("Mensagem Lida: [" + mensagem + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
