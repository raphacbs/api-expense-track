package com.coelho.brasileiro.expensetrack.util;

public interface BusinessCode {
    interface UserCodes{
        Integer USER = 100000;
        Integer PASSWORD_INVALID = USER + 10;
        Integer USER_ALREADY_EXISTS = USER + 20;
        Integer USER_NOT_FOUND = USER + 30;
        Integer EMAIL_NOT_FOUND = USER + 40;
    }

    interface CategoryCodes {
        Integer CATEGORY = 200000;
        Integer CATEGORY_ALREADY_EXISTS = CATEGORY + 10;
        Integer CATEGORY_NOT_FOUND = CATEGORY + 20;
        Integer CATEGORY_INVALID_TYPE = CATEGORY + 30;
    }
    interface BudgetCodes {
        Integer BUDGET = 300000;
        Integer BUDGET_NOT_FOUND = BUDGET + 10;
        Integer BUDGET_INVALID_TYPE = BUDGET + 20;
        Integer BUDGET_INVALID_FREQUENCY = BUDGET + 30;
        Integer BUDGET_INVALID_DATE = BUDGET + 40;
        Integer BUDGET_INVALID_AMOUNT = BUDGET + 50;
        Integer BUDGET_INVALID_PARENT_ID = BUDGET + 60;
        Integer BUDGET_INVALID_CATEGORY_ID = BUDGET + 70;
        Integer BUDGET_INVALID_STATUS = BUDGET + 80;
        Integer BUDGET_INVALID_START_DATE = BUDGET + 90;
        Integer BUDGET_INVALID_END_DATE = BUDGET + 100;
        Integer BUDGET_INVALID_RECURRING_BUDGET = BUDGET + 110;
        Integer BUDGET_INVALID_RECURRING_BUDGET_FREQUENCY = BUDGET + 120;

    }

    interface  PaymentMethodCodes {
        Integer PAYMENT_METHOD = 400000;
        Integer PAYMENT_METHOD_ALREADY_EXISTS = PAYMENT_METHOD + 10;
        Integer PAYMENT_METHOD_NOT_FOUND = PAYMENT_METHOD + 20;
    }
}
