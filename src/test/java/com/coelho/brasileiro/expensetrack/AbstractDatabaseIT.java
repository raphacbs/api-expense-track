package com.coelho.brasileiro.expensetrack;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@Sql(scripts = {"classpath:init-database.sql", "classpath:DML.sql"})
public abstract class AbstractDatabaseIT {
    @Container
    protected PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");
    @InjectMocks
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup() {
        postgres.start();
    }
}