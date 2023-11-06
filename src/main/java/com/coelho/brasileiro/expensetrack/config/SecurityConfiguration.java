package com.coelho.brasileiro.expensetrack.config;


import com.coelho.brasileiro.expensetrack.filter.FilterChainExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
                .antMatchers(HttpMethod.GET, "/api/v1/categories").permitAll()
                .and().cors().and().csrf().disable();
    }
}
