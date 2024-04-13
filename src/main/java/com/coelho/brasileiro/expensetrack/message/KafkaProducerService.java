package com.coelho.brasileiro.expensetrack.message;

import com.coelho.brasileiro.expensetrack.input.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerService<T> {
    @Autowired
    private  KafkaTemplate<String, Input> kafkaTemplate;

    public void sendMessage(String topic, T message) {
        kafkaTemplate.send(topic, message.getClass().getSimpleName(), (Input) message);
    }
}
