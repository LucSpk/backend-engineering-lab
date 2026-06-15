package com.lucas.alves;

import java.time.Duration;
import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class KafkaConsumerService {
    private final KafkaConsumer<String, String> consumer;
    private static final String TOPIC = "mensagens";

    private volatile boolean running = true;

    public KafkaConsumerService(KafkaConsumer<String, String> consumer) {
        this.consumer = consumer;
        consumer.subscribe(Arrays.asList(TOPIC));
    }

    @PostConstruct
    public void start() {
        Thread consumerThread = new Thread(this::read);
        consumerThread.setName("kafka-consumer-thread");
        consumerThread.start();
    }

    @PreDestroy
    public void stop() {
        running = false;
        consumer.wakeup(); // interrompe um poll() bloqueado
        consumer.close();
    }

    @PostConstruct
    public void read() {
        while(running){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Mensagem Lida: " + record.value());
            }
        }
    }
}
