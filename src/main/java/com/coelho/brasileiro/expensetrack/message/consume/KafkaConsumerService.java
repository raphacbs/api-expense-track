package com.coelho.brasileiro.expensetrack.message.consume;

import com.coelho.brasileiro.expensetrack.context.DefaultContext;
import com.coelho.brasileiro.expensetrack.dto.Dto;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.Input;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;

public class KafkaConsumerService<T> {
    private String topicName;
    private ConcurrentMessageListenerContainer<String, String> container;
    private final static Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);
    private String key;

    private ConcurrentKafkaListenerContainerFactory<String, T> factory;

    private AbstractHandler handler;

    protected KafkaConsumerService(ConcurrentKafkaListenerContainerFactory<String, T> factory,
                                   String topicName,
                                   AbstractHandler handler,
                                   String key ) {
        this.factory = factory;
        this.topicName = topicName;
        this.handler = handler;
        this.key = key;
        consume();
    }

    public void consume() {
        ConcurrentMessageListenerContainer<String, T> container = factory.createContainer(topicName);
        container.setupMessageListener((MessageListener<String, T>) message -> {
            DefaultContext context = DefaultContext.builder().build();
            
           if(key.toLowerCase().contains("input")){
               context.setInput(key, (Input) message.value());
           } else if (key.toLowerCase().contains("dto")) {
               context.setDto(key, (Dto) message.value());
           }else {
               context.setEntity(key, (IEntity) message.value());
           }

            handler.handle(context);
            log.info("Mensagem recebida: {} ", message.value());
        });
        container.start();
    }

}
