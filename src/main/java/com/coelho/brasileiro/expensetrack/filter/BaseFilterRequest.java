package com.coelho.brasileiro.expensetrack.filter;

import com.coelho.brasileiro.expensetrack.handler.actions.category.behavior.Params;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class BaseFilterRequest<BODY> {
    protected String pageNo = "0";
    protected String pageSize = "10";
    protected String sortBy = "name";
    protected String sortDir = "asc";
    private final HttpServletRequest request;
    private Map<String, String> params;
    private Map<String, String> headers;
    private BODY body;
    private Class<BODY> bodyClass;

    public BaseFilterRequest(HttpServletRequest request) {
        this.request = request;
        extractParamsAndHeaders();
        extractBody();
        build();
    }

    private void extractParamsAndHeaders() {
        params = new HashMap<>();
        headers = new HashMap<>();
        request.getParameterMap().forEach((key, value) -> params.put(key, value[0]));
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> headers.put(headerName, request.getHeader(headerName)));
    }

    private void extractBody() {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String body = stringBuilder.toString();
        ObjectMapper mapper = new ObjectMapper();

        try {
            ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
            Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
            Class<BODY> bodyClass = (Class<BODY>) actualTypeArguments[0];

            if (bodyClass != null && !body.isEmpty()) {
                this.body = mapper.readValue(body, bodyClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void build() {
        Class<?> currentClass = this.getClass();
        while (currentClass != null) {
            Field[] fields = currentClass.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                if (params.containsKey(fieldName)) {
                    String fieldValue = params.get(fieldName);
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