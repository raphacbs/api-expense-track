package com.coelho.brasileiro.expensetrack.message.consume;

import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;

import java.util.HashMap;
import java.util.Map;


public class ConsumerTopicBuilder<T> {
    private String topicName;
    private AbstractHandler handler;
    private final Logger log = LoggerFactory.getLogger(ConsumerTopicBuilder.class);
    private KafkaProperties kafkaProperties;
    private Class<T> type;
    private ConcurrentKafkaListenerContainerFactory<String, String> factory;
    private SeekToCurrentErrorHandler errorHandler;


    public ConsumerTopicBuilder(Class<T> type) {

        this.type = type;
    }

    public ConsumerTopicBuilder<T> sourceTopic(String topicName) {
        this.topicName = topicName;
        return this;
    }

    public ConsumerTopicBuilder<T> kafkaProperties(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
        return this;
    }

    public ConsumerTopicBuilder<T> handle(AbstractHandler handler) {
        this.handler = handler;
        return this;
    }

    public ConsumerTopicBuilder<T> errorHandler(SeekToCurrentErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
        return this;
    }

    public KafkaConsumerService<T> build() {
        ConcurrentKafkaListenerContainerFactory<String, T> factory = kafkaListenerContainerFactory();
        log.info("Iniciando inscrição para consumir mensagens do tópico: {}", topicName);
        return new KafkaConsumerService<>(factory, topicName, handler, getKey());
    }

    private ConcurrentKafkaListenerContainerFactory<String, T> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setRecordFilterStrategy(record -> {
            System.out.println("record = " + record.value());
            return record.value() != null;
        });
        return factory;
    }

    private ConsumerFactory<String, T> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>(kafkaProperties.buildConsumerProperties());
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class);
        configProps.put("value.deserializer.type", type);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    private String getKey(){
        String className = type.getSimpleName();
        String[] words = StringUtils.splitByCharacterTypeCamelCase(className);
        return String.join("_", words).toUpperCase();
    }


}
