package com.coelho.brasileiro.expensetrack.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BudgetDto  implements  Dto{

    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
    private UUID userId;
    private UUID categoryId;
    private String categoryName;
    private Double amount;
    private String notes;
    private Boolean isDeleted;
    private String parentId;
}
