package com.zorvyn.xpensify.modules.transaction;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */

import com.zorvyn.xpensify.modules.transaction.dto.ResponseTransactionDto;
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
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Tag(name = "Transactions", description = "Financial record endpoints — role-gated per operation")
public class TransactionController {

    private final TransactionService transactionService;

    // fetch a paginated list of records narrowed down by type, category, or date range
    @Operation(summary = "Endpoint to retrieve all transactions with optional filters for type, category, and date range")
    @GetMapping
//    @PreAuthorize("hasAnyRole('VIEWER', 'ANALYST', 'ADMIN')")
    public ResponseEntity<Page<TransactionResponse>> getTransactions(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(transactionService.getTransactions(type, category, from, to, page, size));
    }

    // get the full details of one transaction using its unique ID
    @Operation(summary = "Endpoint to retrieve a single transaction by its ID")
    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('VIEWER', 'ANALYST', 'ADMIN')")
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getById(id));
    }

    // add a new income or expense entry to the financial records
    @Operation(summary = "Endpoint to create a new financial transaction with amount, type, category, and date")
    @PostMapping
//    @PreAuthorize("hasAnyRole('ANALYST', 'ADMIN')")
    public ResponseEntity<ResponseTransactionDto> createTransaction(
            @Valid @RequestBody RequestTransaction request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.create(request));
    }

    // overwrite all fields of an existing transaction with new values
    @Operation(summary = "Endpoint to update an existing transaction by its ID")
    @PutMapping("/{id}")
//    @PreAuthorize("hasAnyRole('ANALYST', 'ADMIN')")
    public ResponseEntity<ResponseTransactionDto> updateTransaction(
            @PathVariable Long id,
            @Valid @RequestBody RequestTransaction request) {
        return ResponseEntity.ok(transactionService.update(id, request));
    }

    // soft-delete a transaction so it disappears from views but history stays intact
    @Operation(summary = "Endpoint to soft-delete a transaction by its ID, preserving historical data")
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}