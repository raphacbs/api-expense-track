package com.coelho.brasileiro.expensetrack.input;

public interface Input {
    void addHeader(String header, Object value);
    Object getHeader(String header);
}
