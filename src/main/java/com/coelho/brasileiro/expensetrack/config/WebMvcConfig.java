package com.coelho.brasileiro.expensetrack.config;

import com.coelho.brasileiro.expensetrack.handle.CustomInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//       registry.addInterceptor(new CustomInterceptor());
    }
}
