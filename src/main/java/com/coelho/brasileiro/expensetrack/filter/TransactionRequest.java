package com.coelho.brasileiro.expensetrack.filter;

import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.model.StatusTransactionEnum;
import com.coelho.brasileiro.expensetrack.model.TransactionTypeEnum;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Enumerated;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class TransactionRequest extends BaseFilterRequest<TransactionInput> {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;
    private String description;
    private UUID categoryId;
    private UUID budgetId;
    private UUID paymentMethodId;
    private UUID groupId;
    private UUID merchant;
    private UUID transactionId;
    private List<String> status;
    private List<String> tags;
    @Enumerated
    private TransactionTypeEnum type;

    public TransactionRequest(HttpServletRequest request) {
        super(request);
    }

    public TransactionRequest(HttpServletRequest request, UUID transactionId) {
        super(request);
        this.transactionId = transactionId;
    }

    @Override
    public String getSortBy() {
        return "description";
    }

    public List<StatusTransactionEnum> getStatusEnums() {
        if (status == null) {
            return null;
        }
        return status.stream()
                .map(StatusTransactionEnum::valueOf)
                .collect(Collectors.toList());
    }

}