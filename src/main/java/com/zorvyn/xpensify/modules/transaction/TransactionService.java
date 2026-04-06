package com.zorvyn.xpensify.modules.transaction;

import com.zorvyn.xpensify.modules.transaction.dto.CreateTransactionDto;
import com.zorvyn.xpensify.modules.transaction.dto.ResponseTransactionDto;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */

@Service
public class TransactionService {
    public @Nullable Page<ResponseTransactionDto> getTransactions(String type, String category, LocalDate from, LocalDate to, int page, int size) {

        return null;
    }

    public @Nullable ResponseTransactionDto getById(Long id) {
        return null;
    }

    public @Nullable ResponseTransactionDto create(@Valid CreateTransactionDto request) {
        return null;
    }

    public @Nullable ResponseTransactionDto update(Long id, @Valid CreateTransactionDto request) {
        return null;
    }

    public void softDelete(Long id) {
        
    }
}
