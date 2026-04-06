package com.zorvyn.xpensify.modules.transaction;

import com.zorvyn.xpensify.modules.transaction.dto.CommonTransactionDto;
import com.zorvyn.xpensify.modules.transaction.dto.CreateTransactionDto;
import com.zorvyn.xpensify.modules.transaction.dto.ResponseTransactionDto;
import com.zorvyn.xpensify.modules.user.User;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */
public class TransactionDtoMapper {

    // Entity → ResponseDto
    public static ResponseTransactionDto toResponseDto(Transactions transaction) {
        if (transaction == null) return null;

        return ResponseTransactionDto.builder()
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .categoryId(transaction.getCategory().getId())
                .categoryName(transaction.getCategory().getName())
                .recordDate(transaction.getRecordDate())
                .note(transaction.getNote())
                .id(transaction.getId())
                .createdAt(transaction.getCreatedAt())
//                .updatedAt(transaction.getUpdatedAt())
                .build();
    }

    // CreateDto → Entity
    public static Transactions toEntity(CreateTransactionDto dto, Category category, User user) {
        if (dto == null) return null;

        return Transactions.builder()
                .amount(dto.getAmount())
                .type(dto.getType())
                .category(category)
                .recordDate(dto.getRecordDate())
                .note(dto.getNote())
                .user(user)
                .build();
    }

    // update only non-null fields on existing entity
    public static void updateEntity(Transactions transaction, CommonTransactionDto dto, Category category) {
        if (dto.getAmount() != null)     transaction.setAmount(dto.getAmount());
        if (dto.getType() != null)       transaction.setType(dto.getType());
        if (dto.getRecordDate() != null) transaction.setRecordDate(dto.getRecordDate());
        if (dto.getNote() != null)       transaction.setNote(dto.getNote());
        if (category != null)            transaction.setCategory(category);
    }

}
