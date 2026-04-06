package com.zorvyn.xpensify.modules.categories.dto;

import com.zorvyn.xpensify.core.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCategoryDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Type is required")
    private TransactionType type;

    private String description;
}