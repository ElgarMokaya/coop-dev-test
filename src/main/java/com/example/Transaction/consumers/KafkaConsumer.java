package com.example.Transaction.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "transaction_topic", groupId = "transaction_group")
    public void consume(String message) {
        System.out.println("Kafka Message Consumed: " + message);
    }
}
