package com.coelho.brasileiro.expensetrack;

import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class AbstractIntegrationTest extends AbstractDatabaseIT {

    @Container
    protected KafkaContainer kafka = new KafkaContainer();

    @BeforeEach
    public void setup() {
        kafka.start();
    }
}
