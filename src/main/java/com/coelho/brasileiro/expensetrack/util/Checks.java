package com.coelho.brasileiro.expensetrack.util;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.exception.BusinessException;

import java.util.function.Predicate;

public class Checks {
    public static boolean isNull(Object object) {
        return object == null;
    }

    public static void check(Predicate<Context> predicate, Context context, Integer  code) {
        if (!predicate.test(context)) {
            throw new BusinessException(code);
        }
    }

    public static boolean isNotNull(Object object) {
        return object != null;
    }
    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

}
