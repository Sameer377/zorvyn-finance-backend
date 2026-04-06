package com.zorvyn.xpensify.modules.transaction.dto;

import com.zorvyn.xpensify.core.enums.TransactionStatus;
import com.zorvyn.xpensify.core.enums.TransactionType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionFilter {

    private Long id;
    private Long userId;
    private TransactionType type;
    private TransactionStatus status;
    private String category;
    private String fromAccountNumber;
    private String toAccountNumber;
    private LocalDate from;
    private LocalDate to;
    private Long createdBy;
    private Boolean deleted;
}