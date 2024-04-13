package com.coelho.brasileiro.expensetrack.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transaction")
@Entity(name = "Transaction")
public class Transaction implements EntityDeletable {



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

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false)
    private Integer installments;

    @Column(nullable = false)
    private Integer currentInstallments;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "parent_id")
    private UUID parentId;

    @Column
    private Boolean isRecurring;

    @Column
    private String merchant;

    @Enumerated(EnumType.STRING)
    @Column
    private StatusTransactionEnum status;

    @Column
    private String tags;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "group_id")
    private UUID groupId;
}
