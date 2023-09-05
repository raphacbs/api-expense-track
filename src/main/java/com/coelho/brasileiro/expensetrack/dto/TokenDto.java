package com.coelho.brasileiro.expensetrack.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto implements Dto{
    private String token;
    private String message;
    private String expiredAt;

}
