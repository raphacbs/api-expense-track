package com.coelho.brasileiro.expensetrack.config;

import com.coelho.brasileiro.expensetrack.handle.transaction.CreateTransactionFromRecurringTransactionHandler;
import com.coelho.brasileiro.expensetrack.handle.transaction.SaveRecurringTransactionHandler;
import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.message.consume.ConsumerTopicBuilder;
import com.coelho.brasileiro.expensetrack.message.consume.KafkaConsumerService;
import com.coelho.brasileiro.expensetrack.model.RecurringTransaction;
import com.coelho.brasileiro.expensetrack.util.PropertiesConfig;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ConsumerTopicConfig {

    private final PropertiesConfig config;
    private final KafkaProperties kafkaProperties;

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
