package com.coelho.brasileiro.expensetrack.service;

import com.coelho.brasileiro.expensetrack.AbstractIntegrationTest;
import com.coelho.brasileiro.expensetrack.TestRepository;
import com.coelho.brasileiro.expensetrack.config.PropertiesConfig;
import com.coelho.brasileiro.expensetrack.input.RecurringTransactionInput;
import com.coelho.brasileiro.expensetrack.message.KafkaProducerService;
import com.coelho.brasileiro.expensetrack.model.RecurringTransaction;
import com.coelho.brasileiro.expensetrack.repository.RecurringTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RecurringTransactionServiceTest extends AbstractIntegrationTest {

    @Mock
    private RecurringTransactionRepository recurringTransactionRepository;

    @Mock
    private KafkaProducerService<RecurringTransactionInput> kafkaProducerService;

    @Mock
    private PropertiesConfig propertiesConfig;

    @InjectMocks
    private RecurringTransactionService recurringTransactionService;

    @Autowired
    private TestRepository testQueryExecutor;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void sendRecurringTransactionsToKafka_whenTransactionsExist_sendsToKafka() {
        // Given
        RecurringTransaction transaction = new RecurringTransaction();
        when(recurringTransactionRepository.findAllByIsActiveTrueAndStartDateBeforeAndEndDateAfterAndLastProcessingBefore(
                any(LocalDate.class), any(LocalDate.class), any(LocalDateTime.class)))
                .thenReturn(Collections.singletonList(transaction));
        when(propertiesConfig.getTopicTransacaoMensalACriar()).thenReturn("TRANSACAO_MENSAL_A_CRIAR");

        // When
        recurringTransactionService.sendRecurringTransactionsToKafka();

        // Then
        ArgumentCaptor<String> topicCaptor = ArgumentCaptor.forClass(String.class);
        verify(kafkaProducerService, times(1)).sendMessage(topicCaptor.capture(), any(RecurringTransactionInput.class));
        assertEquals("TRANSACAO_MENSAL_A_CRIAR", topicCaptor.getValue());

    }

    @Test
    public void sendRecurringTransactionsToKafka_whenNoTransactionsExist_doesNotSendToKafka() {
        // Given
        when(recurringTransactionRepository.findAllByIsActiveTrueAndStartDateBeforeAndEndDateAfterAndLastProcessingBefore(
                any(LocalDate.class), any(LocalDate.class), any(LocalDateTime.class)))
                .thenReturn(Collections.emptyList());

        // When
        recurringTransactionService.sendRecurringTransactionsToKafka();

        // Then
        verify(kafkaProducerService, never()).sendMessage(anyString(), any(RecurringTransactionInput.class));
    }


//    @Test
//    public void findById_whenTransactionExists_returnsTransaction() {
//        // Given
//        UUID id = UUID.fromString("30c56e66-ce59-4d33-b0e4-4a29c4a0c351");
//
//        // When
//        RecurringTransaction transaction = testQueryExecutor.findRecurringTransactionById(id);
//
//        // Then
//        assertNotNull(transaction);
//    }


}