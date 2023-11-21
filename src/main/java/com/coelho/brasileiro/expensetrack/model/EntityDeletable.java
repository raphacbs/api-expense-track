package com.coelho.brasileiro.expensetrack.model;

public interface EntityDeletable extends IEntity{
    Boolean getIsDeleted();
    void setIsDeleted(Boolean isDeleted);
}
