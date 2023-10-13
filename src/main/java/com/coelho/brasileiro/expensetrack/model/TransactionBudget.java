package com.coelho.brasileiro.expensetrack.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transaction_budget")
@Entity(name = "TransactionBudget")
public class TransactionBudget implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private Transaction transaction;

    @OneToOne
    @JoinColumn(name = "budget_id", referencedColumnName = "id")
    private Budget budget;


}
