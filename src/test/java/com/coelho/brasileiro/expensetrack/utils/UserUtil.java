package com.coelho.brasileiro.expensetrack.utils;

import com.coelho.brasileiro.expensetrack.model.User;
import com.coelho.brasileiro.expensetrack.dto.request.LoginRequest;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserUtil {

    public static LoginRequest createLoginRequest(){
        return new LoginRequest("test@email.com",
                "123456");
    }

    public static User.UserBuilder createUserBuilder(){
        return User.builder()
                .id(UUID.randomUUID())
                .email("test@email.com")
                .createdAt(LocalDateTime.now())
                .password("123456")
                .firstName("Teste")
                .googleAccountId(UUID.randomUUID().toString())
                .lastName("Teste");
    }
}
