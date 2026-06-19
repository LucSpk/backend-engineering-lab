package com.lucas.alves;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class KafkaProducerService {
    private final KafkaProducer<String, String> producer;
    private static final String TOPIC = "mensagens";
    
    public KafkaProducerService() {
        this.producer = KafkaProducerConfig.create();
    }

    public void send(Temperatura mensagem) {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("Envia mensagem: " + mensagem);
        try {
            String json = mapper.writeValueAsString(mensagem);
            producer.send(new ProducerRecord<>(TOPIC, json));
        } catch(Exception ex) {
            System.out.println("Mensagem não enviada!");
        }
    }

    @Scheduled(fixedDelay = 5000)
    private void enviaMenagem() {
        send(this.getLeitura());
    }

    private Temperatura getLeitura() {
        Double temp = 27d;

        temp += new Random().nextDouble(1.5);
        temp -= new Random().nextDouble(1.5);

        return new Temperatura("Celcius (C°)", String.format("%.2f", temp), LocalDateTime.now().toString());
    }

}

record Temperatura(String unidade, String temp, String timeStamp) {
    @Override
    public final String toString() {
        return timeStamp + " - " + temp + " " + unidade;
    }
}
