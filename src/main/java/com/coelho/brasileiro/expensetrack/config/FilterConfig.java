package com.coelho.brasileiro.expensetrack.config;

import com.coelho.brasileiro.expensetrack.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean filter= new FilterRegistrationBean();
        filter.setFilter(new JwtFilter());

        // Especifique a URL que deseja filtrar
        filter.addUrlPatterns("/api/v1/user/update/*");
        filter.addUrlPatterns("/api/v1/budgets/*");
        filter.addUrlPatterns("/api/v1/categories/*");
        filter.addUrlPatterns("/api/v1/payment-methods/*");
        filter.addUrlPatterns("/api/v1/transactions/*");
        return filter;
    }
}
