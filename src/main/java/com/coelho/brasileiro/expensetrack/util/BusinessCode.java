package com.coelho.brasileiro.expensetrack.util;

public interface BusinessCode {
    interface UserCodes{
        Integer USER = 100000;
        Integer USER_OR_PASSWORD_INVALID = USER + 10;
        Integer USER_ALREADY_EXISTS = USER + 20;
        Integer USER_NOT_FOUND = USER + 30;
    }

    interface CategoryCodes {
        Integer CATEGORY = 200000;
        Integer CATEGORY_ALREADY_EXISTS = CATEGORY + 10;
        Integer CATEGORY_NOT_FOUND = CATEGORY + 20;
    }
}
