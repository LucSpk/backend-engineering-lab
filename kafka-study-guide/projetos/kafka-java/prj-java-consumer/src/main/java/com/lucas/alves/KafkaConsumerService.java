package com.lucas.alves;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private final KafkaConsumer<String, String> consumer;
    private static final String TOPIC = "mensagens";

    public KafkaConsumerService(KafkaConsumer consumer) {
        this.consumer = consumer;
    }

    @KafkaListener(topics = TOPIC, groupId = "java-consumer-group")
    public void read(String mensagem) {
        System.out.println("Mensagem Lida: [" + mensagem + "]");
    }
}
