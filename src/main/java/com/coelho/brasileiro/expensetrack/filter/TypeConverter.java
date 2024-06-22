package com.coelho.brasileiro.expensetrack.filter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public enum TypeConverter {
    INT(int.class, Integer::parseInt),
    INTEGER(Integer.class, Integer::parseInt),
    LONG(long.class, Long::parseLong),
    LONG_OBJ(Long.class, Long::parseLong),
    DOUBLE(double.class, Double::parseDouble),
    DOUBLE_OBJ(Double.class, Double::parseDouble),
    BOOLEAN(boolean.class, Boolean::parseBoolean),
    BOOLEAN_OBJ(Boolean.class, Boolean::parseBoolean),
    LOCAL_DATE(LocalDate.class, LocalDate::parse),
    UUID_TYPE(UUID.class, UUID::fromString),
    LIST_OF_STRINGS(List.class, value -> Arrays.asList(value.split(",")));

    private final Class<?> type;
    private final Function<String, ?> converter;

    TypeConverter(Class<?> type, Function<String, ?> converter) {
        this.type = type;
        this.converter = converter;
    }

    public static Object convert(Class<?> type, String value) {
        for (TypeConverter converter : values()) {
            if (converter.type.equals(type)) {
                return converter.converter.apply(value);
            }
        }
        return value;
    }
}