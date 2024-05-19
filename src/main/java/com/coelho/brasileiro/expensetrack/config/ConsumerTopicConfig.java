package com.coelho.brasileiro.expensetrack.config;

import com.coelho.brasileiro.expensetrack.handler.transaction.CreateTransactionFromRecurringTransactionHandler;
import com.coelho.brasileiro.expensetrack.handler.transaction.SaveRecurringTransactionHandler;
import com.coelho.brasileiro.expensetrack.input.Input;
import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.message.consume.ConsumerTopicBuilder;
import com.coelho.brasileiro.expensetrack.message.consume.KafkaConsumerService;
import com.coelho.brasileiro.expensetrack.model.RecurringTransaction;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@AllArgsConstructor
public class ConsumerTopicConfig {

    private final PropertiesConfig config;
    private final KafkaProperties kafkaProperties;
    @Autowired
    private KafkaTemplate<String, Input> kafkaTemplate;


    @Bean
    public KafkaConsumerService<TransactionInput> consumerServiceSaveRecurringTransaction(SaveRecurringTransactionHandler handler){
        return new ConsumerTopicBuilder<>(TransactionInput.class)
                .kafkaProperties(kafkaProperties)
                .sourceTopic(config.getTopicTransacaoMensalASalvar())
                .handle(handler)
                .build();
    }

    @Bean
    public KafkaConsumerService<RecurringTransaction> consumerServiceSaveTransaction(CreateTransactionFromRecurringTransactionHandler handler){
        return new ConsumerTopicBuilder<>(RecurringTransaction.class)
                .kafkaProperties(kafkaProperties)
                .sourceTopic(config.getTopicTransacaoMensalACriar())
                .handle(handler)
                .build();
    }


}
