package com.zorvyn.xpensify.modules.transaction.dto;

import com.zorvyn.xpensify.core.enums.TransactionStatus;
import com.zorvyn.xpensify.core.enums.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */

@ToString
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonTransactionDto {
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    @NotNull(message = "Transaction type is required")
    private TransactionType type;

    private String fromAccountNumber;

    private String toAccountNumber;

    private TransactionStatus status;

    private Long categoryId;

    @NotNull(message = "Date is required")
    private LocalDate recordDate;

    private String note;
}
