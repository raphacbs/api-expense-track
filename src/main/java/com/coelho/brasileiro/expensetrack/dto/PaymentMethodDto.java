package com.coelho.brasileiro.expensetrack.dto;

import com.coelho.brasileiro.expensetrack.model.User;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentMethodDto implements Dto{
    private UUID id;
    private String name;
    private String description;
    private Boolean isDeleted;
    private UserDto user;
}
