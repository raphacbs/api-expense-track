package com.coelho.brasileiro.expensetrack.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Dto {
    private UUID id;
    private String email;
    private String createdAt;
    private String firstName;
    private String lastName;
    private String googleAccountId;
}
