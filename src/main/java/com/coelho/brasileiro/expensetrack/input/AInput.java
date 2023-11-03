package com.coelho.brasileiro.expensetrack.input;

import lombok.Builder;

import java.util.HashMap;
import java.util.Map;


public abstract class AInput implements Input{
    private final Map<String, Object> headers = new HashMap<>();
    @Override
    public void addHeader(String header, Object value) {
        headers.put(header,value);
    }

    @Override
    public Object getHeader(String header) {
        return headers.get(header);
    }
}
