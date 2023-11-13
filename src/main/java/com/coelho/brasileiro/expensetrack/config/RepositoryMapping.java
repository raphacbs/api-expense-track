package com.coelho.brasileiro.expensetrack.config;

import com.coelho.brasileiro.expensetrack.model.PaymentMethod;
import com.coelho.brasileiro.expensetrack.repository.PaymentMethodRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RepositoryMapping {

    private final PaymentMethodRepository paymentMethodRepository;

    public RepositoryMapping(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Bean
    public Map<Class<?>, JpaRepository<?, ?>> modelRepositoryMapping() {
        Map<Class<?>, JpaRepository<?, ?>> mapping = new HashMap<>();
        mapping.put(PaymentMethod.class, paymentMethodRepository);
        // Adicione mais mapeamentos conforme necessário para outros modelos
        return mapping;
    }
}
