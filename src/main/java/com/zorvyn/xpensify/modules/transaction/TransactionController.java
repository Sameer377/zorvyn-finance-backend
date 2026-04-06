package com.zorvyn.xpensify.modules.transaction;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */

import com.zorvyn.xpensify.core.PageResponse;
import com.zorvyn.xpensify.core.enums.TransactionStatus;
import com.zorvyn.xpensify.core.enums.TransactionType;
import com.zorvyn.xpensify.modules.transaction.dto.CreateTransactionDto;
import com.zorvyn.xpensify.modules.transaction.dto.ResponseTransactionDto;
import com.zorvyn.xpensify.modules.transaction.dto.TransactionFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
@Tag(name = "Transactions", description = "Financial record endpoints — role-gated per operation")
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Endpoint to retrieve transactions with optional filters")
    @GetMapping
    public ResponseEntity<PageResponse<ResponseTransactionDto>> getTransactions(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) TransactionStatus status,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String fromAccountNumber,
            @RequestParam(required = false) String toAccountNumber,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size
    ) {

        TransactionFilter filter = TransactionFilter.builder()
                .id(id)
                .userId(userId)
                .type(type)
                .status(status)
                .category(category)
                .fromAccountNumber(fromAccountNumber)
                .toAccountNumber(toAccountNumber)
                .from(from)
                .to(to)
                .build();

        return ResponseEntity.ok(
                transactionService.listTransactionByFilter(filter, page, size)
        );
    }

    @Operation(summary = "Endpoint to retrieve a transaction by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTransactionDto> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getById(id));
    }

    @Operation(summary = "Endpoint to create a new transaction")
    @PostMapping
    public ResponseEntity<ResponseTransactionDto> createTransaction(
            @Valid @RequestBody CreateTransactionDto request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionService.create(request));
    }

    @Operation(summary = "Endpoint to update an existing transaction")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseTransactionDto> updateTransaction(
            @PathVariable Long id,
            @Valid @RequestBody CreateTransactionDto request
    ) {
        return ResponseEntity.ok(transactionService.update(id, request));
    }

    @Operation(summary = "Endpoint to soft delete a transaction")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}