package com.coelho.brasileiro.expensetrack.message.consume;

import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;


public class ConsumerTopicBuilder<T> {
    private String topicName;
    private AbstractHandler handler;
    private final Logger log = LoggerFactory.getLogger(ConsumerTopicBuilder.class);
    private KafkaProperties kafkaProperties;
    private Class<T> type;
    private ConcurrentKafkaListenerContainerFactory<String, String> factory;

    public ConsumerTopicBuilder(Class<T> type) {
        this.type = type;
    }

    public ConsumerTopicBuilder<T> topic(String topicName) {
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

    public KafkaConsumerService<T> build() {
        ConcurrentKafkaListenerContainerFactory<String, T> factory = kafkaListenerContainerFactory();
        return new KafkaConsumerService<>(factory, topicName, handler, getKey());
    }

    private ConcurrentKafkaListenerContainerFactory<String, T> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    private ConsumerFactory<String, T> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>(kafkaProperties.buildConsumerProperties());
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, type);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    private String getKey(){
        String className = type.getSimpleName();
        String[] words = StringUtils.splitByCharacterTypeCamelCase(className);
        String key = String.join("_", words).toUpperCase();
        return key;
    }

}
