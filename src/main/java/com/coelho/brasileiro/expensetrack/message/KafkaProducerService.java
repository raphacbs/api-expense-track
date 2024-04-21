package com.coelho.brasileiro.expensetrack.message;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class KafkaProducerService<T> {
    @Autowired
    private  KafkaTemplate<String, T> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    public void sendMessage(String topic, T message) {
        logger.info("Iniciando envio de mensagem para o tópic: {}", topic);
        logger.debug("Mensagem: {}", message);
        List<Header> headerList = new ArrayList<>();
        headerList.add(new RecordHeader(message.getClass().getSimpleName(), message.getClass().getSimpleName().getBytes()));
        ProducerRecord<String, T> record = new ProducerRecord<> (topic,
                null,
                new Date().getTime(),
                message.getClass().getSimpleName(),
                message,
                headerList
                );
        kafkaTemplate.send( record);
        logger.info("Envio concluído com sucesso para o tópico: {}", topic);
    }
}
