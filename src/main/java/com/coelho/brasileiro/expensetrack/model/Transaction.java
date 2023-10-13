package com.coelho.brasileiro.expensetrack.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transaction")
@Entity(name = "Transaction")
public class Transaction implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionTypeEnum type;

    @Column
    private String description;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private Double totalValue;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false)
    private Integer installments;

    @Column(nullable = false)
    private Integer currentInstallments;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(name = "parent_id")
    private UUID parentId;

    @Column(nullable = false)
    private Boolean isRecurring;

    @Column(nullable = false)
    private Boolean isPaid;

    @Column
    private String merchant;

    @Enumerated(EnumType.STRING)
    @Column
    private StatusEnum status;

    @Column
    private String tags;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "payment_method")
    private PaymentMethod paymentMethod;
}
