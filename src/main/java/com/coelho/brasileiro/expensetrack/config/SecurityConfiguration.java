package com.coelho.brasileiro.expensetrack.config;


import com.coelho.brasileiro.expensetrack.filter.FilterChainExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private FilterChainExceptionHandler filterChainExceptionHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(filterChainExceptionHandler, LogoutFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/api/v1/categories",
                        "/api/v1/transactions",
                        "/api/v1/budgets",
                        "/api/v1/payment-methods")
                .permitAll()
                .antMatchers(HttpMethod.POST,
                        "/api/v1/categories",
                        "/api/v1/transactions",
                        "/api/v1/budgets",
                        "/api/v1/payment-methods")
                .permitAll()
                .antMatchers(HttpMethod.PUT,
                        "/api/v1/categories",
                        "/api/v1/transactions",
                        "/api/v1/budgets",
                        "/api/v1/payment-methods")
                .permitAll()
                .antMatchers(HttpMethod.DELETE,
                        "/api/v1/categories",
                        "/api/v1/transactions",
                        "/api/v1/budgets",
                        "/api/v1/payment-methods")
                .permitAll()
                .and().cors().and().csrf().disable();
    }


    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password("{noop}password")
                .roles("USER")
                .build());
        return manager;
    }

}
