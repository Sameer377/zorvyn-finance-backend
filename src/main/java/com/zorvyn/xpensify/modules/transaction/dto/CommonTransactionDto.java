package com.zorvyn.xpensify.modules.transaction.dto;

import com.zorvyn.xpensify.core.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */
public class CommonTransactionDto {
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    @NotNull(message = "Transaction type is required")
    private TransactionType type;

    private Long categoryId;

    @NotNull(message = "Date is required")
    private LocalDate recordDate;

    private String note;
}
