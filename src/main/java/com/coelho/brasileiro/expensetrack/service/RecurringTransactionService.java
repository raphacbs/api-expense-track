package com.coelho.brasileiro.expensetrack.service;

import com.coelho.brasileiro.expensetrack.config.PropertiesConfig;
import com.coelho.brasileiro.expensetrack.input.RecurringTransactionInput;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.message.KafkaProducerService;
import com.coelho.brasileiro.expensetrack.model.RecurringTransaction;
import com.coelho.brasileiro.expensetrack.repository.RecurringTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecurringTransactionService {
    private final RecurringTransactionRepository recurringTransactionRepository;
    private final KafkaProducerService<RecurringTransactionInput> kafkaProducerService;
    private final PropertiesConfig propertiesConfig;
    private final Logger log = LoggerFactory.getLogger(RecurringTransactionService.class);
    private final Converter converter = Converter.INSTANCE;

    public RecurringTransactionService(RecurringTransactionRepository recurringTransactionRepository,
                                       KafkaProducerService<RecurringTransactionInput> kafkaProducerService,
                                       PropertiesConfig propertiesConfig) {
        this.recurringTransactionRepository = recurringTransactionRepository;
        this.kafkaProducerService = kafkaProducerService;
        this.propertiesConfig = propertiesConfig;
    }

    @Scheduled(cron = "0 0 23 L * ?")
    public void sendRecurringTransactionsToKafka() {
        log.info("Buscando transações recorrentes para enviar para o Kafka");
        List<RecurringTransaction> transactions =
                this.recurringTransactionRepository.findAllByIsActiveTrueAndStartDateBeforeAndEndDateAfterAndLastProcessingBefore(
                        LocalDate.now(),
                        LocalDate.now(),
                        LocalDateTime.now()
                );
        log.info("Enviando {} transações recorrentes para o Kafka", transactions.size());
//        transactions.forEach(transaction -> {
//            this.kafkaProducerService.sendMessage(propertiesConfig.getTopicTransacaoMensalACriar(), converter.toInput(transaction));
//            log.info("Transação recorrente enviada para o Kafka: {}", transaction.getId());
//        });
    }

}
