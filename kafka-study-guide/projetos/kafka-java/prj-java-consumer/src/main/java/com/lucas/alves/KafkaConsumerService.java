package com.lucas.alves;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private static final String TOPIC = "mensagens";

    @KafkaListener(topics = TOPIC)
    public void read(Temperatura mensagem) {
        try {
            System.out.println("Temperatura: [" + mensagem.temp() + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

record Temperatura(String unidade, String temp, String timeStamp) {
    @Override
    public final String toString() {
        return timeStamp + " - " + temp + " " + unidade;
    }
}