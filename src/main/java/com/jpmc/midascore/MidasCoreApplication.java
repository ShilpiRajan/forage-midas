package com.jpmc.midascore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.EmbeddedKafkaZKBroker;

@SpringBootApplication
public class MidasCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MidasCoreApplication.class, args);
    }

    @Bean
    public EmbeddedKafkaBroker embeddedKafkaBroker() {
        // Yeh line aapka apna Kafka server 9092 port pe start karegi
        return new EmbeddedKafkaZKBroker(1, true, "transactions")
                .kafkaPorts(9092);
    }
}