package com.coelho.brasileiro.expensetrack.service;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

@Component
public abstract class AService {
    private Map<String, String> userData;

    private final HttpServletRequest request;
    protected AService(HttpServletRequest request) {
        this.request = request;
        userData = (Map<String, String>) request.getAttribute("claims");
    }
    public UUID getUserId() {
        return UUID.fromString(userData.get("id"));
    }
}
