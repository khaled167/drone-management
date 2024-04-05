package com.orange.innovation.dronemanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class DroneManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroneManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
        System.out.println("Logged Kafka Produce Message");
        return args -> {
            kafkaTemplate.send("firstTopic", "Hello Kafka");
            kafkaTemplate.send("secondTopic","Hello Kafka");
        };
    }
}
