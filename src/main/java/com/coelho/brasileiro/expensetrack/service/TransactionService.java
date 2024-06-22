package com.coelho.brasileiro.expensetrack.service;

import com.coelho.brasileiro.expensetrack.context.DefaultContext;
import com.coelho.brasileiro.expensetrack.dto.TransactionDto;
import com.coelho.brasileiro.expensetrack.dto.TransactionResponse;
import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.filter.TransactionRequest;
import com.coelho.brasileiro.expensetrack.flow.transaction.RegisterTransactionBuilder;
import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.FrequencyEnum;
import com.coelho.brasileiro.expensetrack.model.StatusTransactionEnum;
import com.coelho.brasileiro.expensetrack.model.Transaction;
import com.coelho.brasileiro.expensetrack.repository.TransactionCustomRepository;
import com.coelho.brasileiro.expensetrack.repository.TransactionRepository;
import com.coelho.brasileiro.expensetrack.util.FrequencyUtils;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.coelho.brasileiro.expensetrack.util.BusinessCode.TransactionCodes.TRANSACTION_ALREADY_PAID;
import static com.coelho.brasileiro.expensetrack.util.BusinessCode.TransactionCodes.TRANSACTION_NOT_FOUND;
import static com.coelho.brasileiro.expensetrack.util.Checks.isNull;
import static com.coelho.brasileiro.expensetrack.util.Constants.Transaction.TRANSACTION;


@Service
@AllArgsConstructor
public class TransactionService {

    private final RegisterTransactionBuilder registerTransactionBuilder;
    private final TransactionRepository transactionRepository;
    private final Converter converter = Converter.INSTANCE;
    private final TransactionCustomRepository transactionCustomRepository;
    private final UserService userService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TransactionDto saveTransaction(TransactionRequest request) {
        DefaultContext context = DefaultContext.builder().build();
        context.setTransactionInput(request.getBody());
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

    public Transaction createNextInstallmentTransaction(@NotNull Transaction transaction) {
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

    public void setTransactionStatusByDate(@NotNull Transaction transaction) {

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

    public TransactionResponse getTransactionsByPeriodAndFilters(TransactionRequest filterRequest) {
        return converter.toDto(this.transactionCustomRepository.findTransactionsByDateRangeAndStatus(filterRequest,
                filterRequest.getPageable(),
                userService.getUserLogged().getId()
        ));
    }

    public void payTransaction(@NotNull TransactionRequest request) {

        Transaction transaction = this.transactionRepository.findById(request.getTransactionId())
                .orElseThrow(() -> new BusinessException(TRANSACTION_NOT_FOUND));

        if (transaction.getStatus().equals(StatusTransactionEnum.PAID)) {
            throw new BusinessException(TRANSACTION_ALREADY_PAID);
        }

        transaction.setPaymentDate(LocalDate.now());
        transaction.setStatus(StatusTransactionEnum.PAID);
        this.transactionRepository.save(transaction);
    }

    public Transaction editTransaction(UUID transactionId, TransactionInput input) {
        return null;
    }


}
