package com.coelho.brasileiro.expensetrack;

import com.coelho.brasileiro.expensetrack.model.RecurringTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TestRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public RecurringTransaction findRecurringTransactionById(UUID id) {
        String sql = "SELECT * FROM recurring_transaction WHERE id = ?";
        RowMapper<RecurringTransaction> rowMapper = (rs, rowNum) -> {
            RecurringTransaction transaction = new RecurringTransaction();
            // map the result set to your transaction object
            return transaction;
        };
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
