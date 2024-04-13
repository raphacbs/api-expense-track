package com.coelho.brasileiro.expensetrack.input;



import java.io.Serializable;

public interface Input   {
    void addHeader(String header, Object value);
    Object getHeader(String header);

}
