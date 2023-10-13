package com.coelho.brasileiro.expensetrack.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transaction_money_box")
@Entity(name = "TransactionMoneyBox")
public class TransactionMoneyBox implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private Transaction transaction;

    @OneToOne
    @JoinColumn(name = "money_box_id", referencedColumnName = "id")
    private MoneyBox moneyBox;


}
