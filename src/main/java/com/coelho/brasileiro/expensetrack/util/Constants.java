package com.coelho.brasileiro.expensetrack.util;

import org.springframework.beans.factory.annotation.Value;

public class Constants {

    public interface User{
        String USER = "USER";
        String USER_INPUT = USER+"_INPUT";
        String USER_DTO = USER+"_DTO";
        String USER_REQUEST = USER+"_REQUEST";

    }

    public interface Token{
        String TOKEN = "TOKEN";
        String TOKEN_DTO = TOKEN+"_DTO";

    }

    public interface Category{
        String CATEGORY = "CATEGORY";
        String CATEGORY_INPUT = CATEGORY+"_INPUT";
        String CATEGORY_DTO = CATEGORY+"_DTO";
        String CATEGORY_REQUEST = CATEGORY+"_REQUEST";
    }

    public interface Budget
    {
        String BUDGET = "BUDGET";
        String BUDGET_INPUT = BUDGET+"_INPUT";
        String BUDGET_DTO = BUDGET+"_DTO";
        String BUDGET_REQUEST = BUDGET+"_REQUEST";
    }

    public interface RecurringBudget {
        String RECURRING_BUDGET = "RECURRING_BUDGET";
        String RECURRING_BUDGET_INPUT = RECURRING_BUDGET + "_INPUT";
        String RECURRING_BUDGET_DTO = RECURRING_BUDGET + "_DTO";
        String RECURRING_BUDGET_REQUEST = RECURRING_BUDGET + "_REQUEST";
    }

    public interface PaymentMethod {
        String PAYMENT_METHOD = "PAYMENT_METHOD";
        String PAYMENT_METHOD_INPUT = PAYMENT_METHOD + "_INPUT";
        String PAYMENT_METHOD_DTO = PAYMENT_METHOD + "_DTO";
        String PAYMENT_METHOD_REQUEST = PAYMENT_METHOD + "_REQUEST";
    }

    public interface MoneyBox {
        String MONEY_BOX = "MONEY_BOX";
        String MONEY_BOX_INPUT = MONEY_BOX + "_INPUT";
        String MONEY_BOX_DTO = MONEY_BOX + "_DTO";
        String MONEY_BOX_REQUEST = MONEY_BOX + "_REQUEST";
    }

    public interface Transaction {
        String TRANSACTION = "TRANSACTION";
        String TRANSACTIONS = "TRANSACTIONS";
        String TRANSACTION_INPUT = TRANSACTION + "_INPUT";
        String TRANSACTION_DTO = TRANSACTION + "_DTO";

    }

    public interface Topic {
        String TOPIC_FREQUENCIA_MENSAL_A_PROCESSAR = "FREQUENCIA_MENSAL_A_PROCESSAR";


    }


}
