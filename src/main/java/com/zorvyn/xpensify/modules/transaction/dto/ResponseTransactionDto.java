package com.zorvyn.xpensify.modules.transaction.dto;

import com.zorvyn.xpensify.core.enums.TransactionType;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */
public class ResponseTransactionDto extends CommonTransactionDto{
    private Long id;
    private String categoryName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
