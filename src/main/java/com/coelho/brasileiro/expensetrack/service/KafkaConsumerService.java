package com.coelho.brasileiro.expensetrack.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.coelho.brasileiro.expensetrack.util.Constants.Topic.TOPIC_FREQUENCIA_MENSAL_A_PROCESSAR;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = TOPIC_FREQUENCIA_MENSAL_A_PROCESSAR)
    public void consume(ConsumerRecord<String, String> record) {
        System.out.println("Mensagem recebida: " + record.value());
    }
}
