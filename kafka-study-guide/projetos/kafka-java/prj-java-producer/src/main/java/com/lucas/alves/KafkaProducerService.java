package com.lucas.alves;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaProducer<String, String> producer;
    private static final String TOPIC = "mensagens";

    private int num = 0;
    
    public KafkaProducerService() {
        this.producer = KafkaProducerConfig.create();
    }

    public void send(String mensagem) {
        System.out.println("Enviando a mensagem: [" + mensagem + "]...");
        producer.send(new ProducerRecord<>(TOPIC, mensagem));
    }

    @Scheduled(fixedDelay = 5000)
    private void enviaMenagem() {
        send("Mensagem " + num);
        num ++;
    }
}
