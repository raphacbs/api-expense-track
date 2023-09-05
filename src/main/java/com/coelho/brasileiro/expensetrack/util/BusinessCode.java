package com.coelho.brasileiro.expensetrack.util;

public interface BusinessCode {
    interface UserCodes{
        Integer USER = 100000;
        Integer USER_OR_PASSWORD_INVALID = USER + 10;
    }
}
