package com.coelho.brasileiro.expensetrack.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto implements Dto{
    private UUID id;
    private String name;
    private String description;
    private String color;
    private String type;
    private boolean isDeleted;
}
