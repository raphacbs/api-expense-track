package com.coelho.brasileiro.expensetrack.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "recurring_transaction")
@Entity(name = "RecurringTransaction")
public class RecurringTransaction implements EntityDeletable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionTypeEnum type;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private Boolean isFixedValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FrequencyEnum frequency;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column
    private LocalDateTime lastProcessing;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "group_id")
    private UUID groupId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_method")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "money_box_id")
    private MoneyBox moneyBox;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

}
