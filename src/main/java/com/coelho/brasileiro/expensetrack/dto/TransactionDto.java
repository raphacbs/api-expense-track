package com.coelho.brasileiro.expensetrack.dto;


import com.coelho.brasileiro.expensetrack.model.StatusTransactionEnum;
import com.coelho.brasileiro.expensetrack.model.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto implements Dto {

    private UUID id;

    private TransactionTypeEnum type;

    private String description;

    private Double value;

    private Double totalValue;

    private LocalDate paymentDate;

    private LocalDate dueDate;

    private Boolean isDeleted;

    private Integer installments;

    private Integer currentInstallments;

    private LocalDateTime createdAt;

    private UUID parentId;

    private Boolean isRecurring;

    private UUID groupId;

    private String merchant;

    private StatusTransactionEnum status;

    private String tags;

    private UserDto user;

    private CategoryDto category;

    private PaymentMethodDto paymentMethod;
    

}

