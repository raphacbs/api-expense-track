package com.coelho.brasileiro.expensetrack.repository;

import com.coelho.brasileiro.expensetrack.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class CustomTransactionRowMapper implements RowMapper<Transaction> {

    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();

        transaction.setId(UUID.fromString(rs.getString("id")));
        transaction.setType(TransactionTypeEnum.valueOf(rs.getString("type")));
        transaction.setDescription(rs.getString("description"));
        transaction.setValue(rs.getDouble("value"));
        transaction.setTotalValue(rs.getDouble("total_value"));
        transaction.setPaymentDate(rs.getObject("payment_date", LocalDate.class));
        transaction.setDueDate(rs.getObject("due_date", LocalDate.class));
        transaction.setIsDeleted(rs.getBoolean("is_deleted"));
        transaction.setInstallments(rs.getInt("installments"));
        transaction.setCurrentInstallments(rs.getInt("current_installments"));
        transaction.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
        transaction.setParentId(rs.getObject("parent_id") != null ? UUID.fromString(rs.getString("parent_id")) : null);
        transaction.setIsRecurring(rs.getBoolean("is_recurring"));
        transaction.setMerchant(rs.getString("merchant"));
        transaction.setStatus(StatusTransactionEnum.valueOf(rs.getString("status")));
        transaction.setTags(rs.getString("tags"));

        // Assuming User, Category, and PaymentMethod have their own mappers or simple constructors
        transaction.setUser(User.builder().id(UUID.fromString(rs.getString("user_id"))).build());
        transaction.setCategory(Category.builder().id(UUID.fromString(rs.getString("category_id"))).build());
        transaction.setPaymentMethod(PaymentMethod.builder().id(UUID.fromString(rs.getString("payment_method"))).build());

        transaction.setGroupId(rs.getObject("group_id") != null ? UUID.fromString(rs.getString("group_id")) : null);

        return transaction;
    }
}
