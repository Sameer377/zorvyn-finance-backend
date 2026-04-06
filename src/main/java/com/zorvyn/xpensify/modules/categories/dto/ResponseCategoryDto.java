package com.zorvyn.xpensify.modules.categories.dto;


import com.zorvyn.xpensify.core.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseCategoryDto {

    private Long id;
    private String name;
    private TransactionType type;
    private String description;
    private LocalDateTime createdAt;
}