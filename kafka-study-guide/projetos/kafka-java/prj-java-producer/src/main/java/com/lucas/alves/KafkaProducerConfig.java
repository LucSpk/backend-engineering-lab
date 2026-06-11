package com.lucas.alves;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {
    
    @Bean
    public static KafkaProducer<String, String> create() {

        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {
            producer.partitionsFor("meu-topico");
            System.out.println("✅ Conexão com o Kafka realizada com sucesso.");
        } catch (Exception e) {
            System.err.println("❌ Erro ao conectar no Kafka: " + e.getMessage());
            throw e;
        }

        return producer;
    }    
}
