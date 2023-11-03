package com.coelho.brasileiro.expensetrack.handle;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Intercepte a requisição aqui
        // Você pode adicionar um parâmetro ao corpo da solicitação
        // O exemplo a seguir adicionará um parâmetro chamado "meuParam" com um valor:
       // request.setAttribute("meuParam", "MeuValor");
        return true; // Retorna true para permitir que a solicitação prossiga para o controller
    }
}
