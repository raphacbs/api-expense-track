package com.coelho.brasileiro.expensetrack.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerInterceptorImpl implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Lógica que será executada antes do método do controlador
        // Você pode modificar a requisição aqui, se necessário

        return true; // Retorna true para continuar o processamento, ou false para interromper
    }


}
