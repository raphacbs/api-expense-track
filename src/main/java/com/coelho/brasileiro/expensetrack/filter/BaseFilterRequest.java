package com.coelho.brasileiro.expensetrack.filter;

import com.coelho.brasileiro.expensetrack.handler.actions.category.behavior.Params;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class BaseFilterRequest {
    protected String pageNo = "0";
    protected String pageSize = "10";
    protected String sortBy = "name";
    protected String sortDir = "asc";


    public BaseFilterRequest(Map<String, String> allParams) {
        Class<?> currentClass = this.getClass();
        while (currentClass != null) {
            Field[] fields = currentClass.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                if (allParams.containsKey(fieldName)) {
                    String fieldValue = allParams.get(fieldName);
                    field.setAccessible(true);
                    try {
                        Object convertedValue = convertToFieldType(field.getType(), fieldValue);
                        field.set(this, convertedValue);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            currentClass = currentClass.getSuperclass();
        }
    }

    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("pageNo", getPageNo());
        params.put("pageSize", getPageSize());
        params.put("sortBy", getSortBy());
        params.put("sortDir", getSortDir());
        return params;
    }

    public Pageable getPageable() {
        return Params.getPageable(getParams());
    }

    private Object convertToFieldType(Class<?> type, String fieldValue) {
        return TypeConverter.convert(type, fieldValue);
    }
}