package com.coelho.brasileiro.expensetrack.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("is_fixed_value")
    @Column(nullable = false)
    private Boolean isFixedValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FrequencyEnum frequency;

    @JsonProperty("is_active")
    @Column(nullable = false)
    private Boolean isActive;

    @JsonProperty("start_date")
    @Column(nullable = false)
    private LocalDate startDate;

    @JsonProperty("end_date")
    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    @JsonProperty("created_at")
    private LocalDate createdAt;

    @JsonProperty("last_processing")
    @Column
    private LocalDateTime lastProcessing;

    @Column(nullable = false)
    @JsonProperty("is_deleted")
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty("category_id")
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JsonProperty("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @JoinColumn(name = "group_id")
    @JsonProperty("group_id")
    private UUID groupId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_method")
    @JsonProperty("payment_method")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JsonProperty("money_box_id")
    @JoinColumn(name = "money_box_id")
    private MoneyBox moneyBox;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    @JsonProperty("budget_id")
    private Budget budget;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("due_date")
    private LocalDate dueDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("last_due_date")
    private LocalDate lastDueDate;

    @Column
    private String merchant;

    @Column
    private String tags;


}
