package com.coelho.brasileiro.expensetrack.message.transform;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Transform<T> {
    T toObject() throws JsonProcessingException;
}
