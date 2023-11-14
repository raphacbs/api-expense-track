package com.coelho.brasileiro.expensetrack.model;

import javax.persistence.*;
import java.util.UUID;
@Entity
public abstract class AEntity implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
}
