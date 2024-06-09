package com.coelho.brasileiro.expensetrack.service;

import com.coelho.brasileiro.expensetrack.context.DefaultContext;
import com.coelho.brasileiro.expensetrack.dto.TransactionDto;
import com.coelho.brasileiro.expensetrack.flow.transaction.RegisterTransactionBuilder;
import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.FrequencyEnum;
import com.coelho.brasileiro.expensetrack.model.StatusTransactionEnum;
import com.coelho.brasileiro.expensetrack.model.Transaction;
import com.coelho.brasileiro.expensetrack.repository.TransactionRepository;
import com.coelho.brasileiro.expensetrack.util.FrequencyUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.coelho.brasileiro.expensetrack.util.Checks.isNull;
import static com.coelho.brasileiro.expensetrack.util.Constants.Transaction.TRANSACTION;


@Service
@AllArgsConstructor
public class TransactionService {

    private final RegisterTransactionBuilder registerTransactionBuilder;
    private final TransactionRepository transactionRepository;
    private final Converter converter = Converter.INSTANCE;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TransactionDto saveTransaction(TransactionInput input) {
        DefaultContext context = DefaultContext.builder().build();
        context.setTransactionInput(input);
        context.setEntityNameCurrent(TRANSACTION);
        this.registerTransactionBuilder.create(context).build().run();
        return context.getTransactionDto();
    }

    public boolean deleteTransaction(UUID transactionId) {
    return false;
    }

    public void updateTransaction() {

    }

    public void findTransaction() {

    }

    public void findAllTransactions() {

    }

    public Transaction createNextInstallmentTransaction(Transaction transaction) {
        LocalDateTime nextDate = FrequencyUtils.calculateNextDate(transaction.getDueDate().atStartOfDay(), FrequencyEnum.MONTHLY);
        return Transaction.builder()
                .type(transaction.getType())
                .description(transaction.getDescription())
                .installments(transaction.getInstallments())
                .currentInstallments(transaction.getCurrentInstallments() + 1)
                .dueDate(nextDate.toLocalDate())
                .value(transaction.getValue())
                .totalValue(transaction.getTotalValue())
                .paymentDate(null)
                .category(transaction.getCategory())
                .createdAt(LocalDateTime.now())
                .merchant(transaction.getMerchant())
                .tags(transaction.getTags())
                .status(StatusTransactionEnum.SCHEDULED)
                .paymentMethod(transaction.getPaymentMethod())
                .user(transaction.getUser())
                .isDeleted(false)
                .groupId(transaction.getGroupId())
                .isRecurring(false)
                .parentId(null)
                .build();
    }

    public void setTransactionStatusByDate(Transaction transaction) {

        LocalDate paymentDate = transaction.getPaymentDate();
        LocalDate dueDate = transaction.getDueDate();

        if (isNull(paymentDate)) {
            if (dueDate.isBefore(LocalDate.now())) {
                transaction.setStatus(StatusTransactionEnum.LATE_PAYMENT);
            } else {
                transaction.setStatus(StatusTransactionEnum.SCHEDULED);
            }
        } else {
            transaction.setStatus(StatusTransactionEnum.PAID);
        }
    }

    public List<TransactionDto> getTransactionsByPeriodAndFilters(LocalDate startDate,
                                                               LocalDate endDate,
                                                               UUID categoryId,
                                                                  String description,
                                                               UUID budgetId) {

        return converter.toDto(this.transactionRepository.findByPeriodAndDescription(startDate, endDate, description));
    }

    public boolean payTransaction(UUID transactionId) {
        return false;
    }

    public Transaction editTransaction(UUID transactionId, TransactionInput input) {
        return null;
    }


}
