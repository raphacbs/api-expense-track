package com.coelho.brasileiro.expensetrack.model;

import java.io.Serializable;
import java.util.UUID;

public interface IEntity extends Serializable {
    UUID getId();
    static final long serialVersionUID = 1L;
}
