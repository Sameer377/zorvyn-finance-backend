package com.zorvyn.xpensify.modules.transaction;

import com.zorvyn.xpensify.core.enums.TransactionStatus;
import com.zorvyn.xpensify.modules.categories.Category;
import com.zorvyn.xpensify.modules.transaction.dto.CommonTransactionDto;
import com.zorvyn.xpensify.modules.transaction.dto.CreateTransactionDto;
import com.zorvyn.xpensify.modules.transaction.dto.ResponseTransactionDto;
import com.zorvyn.xpensify.modules.user.User;

import java.util.List;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */
public class TransactionDtoMapper {

    public static ResponseTransactionDto toResponseDto(Transactions transaction) {
        if (transaction == null) return null;

        return ResponseTransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())

                .status(transaction.getStatus())
                .fromAccountNumber(transaction.getFromAccountNumber())
                .toAccountNumber(transaction.getToAccountNumber())

                .categoryId(transaction.getCategory().getId())
                .categoryName(transaction.getCategory().getName())
                .recordDate(transaction.getRecordDate())
                .note(transaction.getNote())
                .createdAt(transaction.getCreatedAt())
                .build();
    }

    public static Transactions toEntity(CreateTransactionDto dto, Category category, User user) {
        if (dto == null) return null;

        return Transactions.builder()
                .amount(dto.getAmount())
                .type(dto.getType())

                .status(dto.getStatus() != null ? dto.getStatus() : TransactionStatus.COMPLETED)
                .fromAccountNumber(dto.getFromAccountNumber())
                .toAccountNumber(dto.getToAccountNumber())

                .category(category)
                .recordDate(dto.getRecordDate())
                .note(dto.getNote())
                .user(user)
                .build();
    }

    public static void updateEntity(Transactions transaction, CommonTransactionDto dto, Category category) {

        if (dto.getAmount() != null)
            transaction.setAmount(dto.getAmount());

        if (dto.getType() != null)
            transaction.setType(dto.getType());

        if (dto.getRecordDate() != null)
            transaction.setRecordDate(dto.getRecordDate());

        if (dto.getNote() != null)
            transaction.setNote(dto.getNote());

        if (category != null)
            transaction.setCategory(category);

        if (dto.getStatus() != null)
            transaction.setStatus(dto.getStatus());

        if (dto.getFromAccountNumber() != null)
            transaction.setFromAccountNumber(dto.getFromAccountNumber());

        if (dto.getToAccountNumber() != null)
            transaction.setToAccountNumber(dto.getToAccountNumber());
    }

    public static List<ResponseTransactionDto> toResponseDtoList(List<Transactions> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            return List.of();
        }
        return transactions.stream()
                .map(TransactionDtoMapper::toResponseDto)
                .toList();
    }
}