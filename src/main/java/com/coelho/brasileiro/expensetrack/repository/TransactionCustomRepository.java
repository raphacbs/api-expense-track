package com.coelho.brasileiro.expensetrack.repository;

import com.coelho.brasileiro.expensetrack.filter.TransactionRequest;
import com.coelho.brasileiro.expensetrack.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TransactionCustomRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TransactionPageImpl<Transaction> findTransactionsByDateRangeAndStatus(TransactionRequest filterRequest, Pageable pageable, UUID userId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM transaction WHERE is_deleted = false ");
        List<Object> params = new ArrayList<>();

        buildBaseQuery(filterRequest, sql, params, userId);
        buildOptionalFilters(filterRequest, sql, params);
        addPagination(sql, params, pageable);

        List<Transaction> transactions = jdbcTemplate.query(
                sql.toString(),
                params.toArray(),
                new CustomTransactionRowMapper());

        int total = getTotalCount(filterRequest, userId);

        BigDecimal balance = getTotalBalance(filterRequest, userId);

        Page<Transaction> page = new PageImpl<>(transactions, pageable, total);

        return new TransactionPageImpl<>(page, balance);
    }

    private void buildBaseQuery(TransactionRequest filterRequest, StringBuilder sql, List<Object> params, UUID userId) {
        sql.append("AND due_date BETWEEN ? AND ? ");
        params.add(filterRequest.getStartDate());
        params.add(filterRequest.getEndDate());

        sql.append("AND user_id = ? ");
        params.add(userId);
    }

    private void buildOptionalFilters(TransactionRequest filterRequest, StringBuilder sql, List<Object> params) {
        if (filterRequest.getDescription() != null) {
            sql.append("AND lower(description) LIKE lower(?) ");
            params.add("%" + filterRequest.getDescription() + "%");
        }

        if (filterRequest.getStatus() != null && !filterRequest.getStatus().isEmpty()) {
            sql.append("AND status IN (");
            String inSql = filterRequest.getStatus().stream()
                    .map(status -> "?")
                    .collect(Collectors.joining(","));
            sql.append(inSql).append(") ");
            params.addAll(filterRequest.getStatus());
        }

        if (filterRequest.getTags() != null) {
            sql.append("AND tags IN (");
            String inSql = filterRequest.getTags().stream()
                    .map(tag -> "?")
                    .collect(Collectors.joining(","));
            sql.append(inSql).append(") ");
            params.addAll(filterRequest.getTags());
        }

        if (filterRequest.getCategoryId() != null) {
            sql.append("AND category_id = ? ");
            params.add(filterRequest.getCategoryId());
        }

        if (filterRequest.getPaymentMethodId() != null) {
            sql.append("AND payment_method = ? ");
            params.add(filterRequest.getPaymentMethodId());
        }

        if (filterRequest.getBudgetId() != null) {
            sql.append("AND budget_id = ? ");
            params.add(filterRequest.getBudgetId());
        }

        if (filterRequest.getGroupId() != null) {
            sql.append("AND group_id = ? ");
            params.add(filterRequest.getGroupId());
        }

        if (filterRequest.getMerchant() != null) {
            sql.append("AND merchant = ? ");
            params.add(filterRequest.getMerchant());
        }

        if (filterRequest.getType() != null) {
            sql.append("AND type = ? ");
            params.add(filterRequest.getType().name());
        }
    }

    private void addPagination(StringBuilder sql, List<Object> params, Pageable pageable) {
        sql.append("LIMIT ? OFFSET ?");
        params.add(pageable.getPageSize());
        params.add(pageable.getOffset());
    }

    private int getTotalCount(TransactionRequest filterRequest, UUID userId) {
        StringBuilder countSql = new StringBuilder("SELECT COUNT(*) FROM transaction WHERE is_deleted = false ");
        countSql.append("AND user_id = ? ");
        countSql.append("AND due_date BETWEEN ? AND ? ");

        List<Object> countParams = List.of(userId,
                filterRequest.getStartDate(),
                filterRequest.getEndDate());

        return jdbcTemplate.queryForObject(
                countSql.toString(),
                countParams.toArray(),
                Integer.class);
    }

    private BigDecimal getTotalBalance(TransactionRequest filterRequest, UUID userId) {
        List<Object> params = new ArrayList<>();
        StringBuilder balanceSql = new StringBuilder("SELECT SUM(CASE WHEN type = 'E' THEN -total_value ELSE total_value END) FROM transaction WHERE is_deleted = false ");
        buildBaseQuery(filterRequest, balanceSql, params, userId);
        buildOptionalFilters(filterRequest, balanceSql, params);

        return jdbcTemplate.queryForObject(
                balanceSql.toString(),
                params.toArray(),
                BigDecimal.class);
    }


}
