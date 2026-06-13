package com.lucas.alves;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

@Configuration
public class KafkaConsumerConfig {
    
    @Bean
    public static KafkaConsumer<String, String> create() {
        Properties props = new Properties();
        // Endereço do seu Kafka Broker
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // Identificador do grupo de consumidores
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "java-consumer-group");
        // Deserializadores para chave e valor
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");

        return new KafkaConsumer<>(props);
    }    
}
