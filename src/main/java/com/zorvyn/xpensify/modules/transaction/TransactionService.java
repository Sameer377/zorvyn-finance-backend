package com.zorvyn.xpensify.modules.transaction;

import com.zorvyn.xpensify.core.PageResponse;
import com.zorvyn.xpensify.exception.NotFoundException;
import com.zorvyn.xpensify.modules.categories.Category;
import com.zorvyn.xpensify.modules.categories.CategoryRepository;
import com.zorvyn.xpensify.modules.transaction.dto.CreateTransactionDto;
import com.zorvyn.xpensify.modules.transaction.dto.ResponseTransactionDto;
import com.zorvyn.xpensify.modules.transaction.dto.TransactionFilter;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */
@RequiredArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    private Specification<Transactions> buildSpecification(TransactionFilter filter) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (filter.getId() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("id"), filter.getId()));
            }

            if (filter.getCreatedBy() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("createdBy"), filter.getCreatedBy()));
            }

            if (filter.getDeleted() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("deleted"), filter.getDeleted()));
            }

            if (filter.getUserId() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("user").get("id"), filter.getUserId()));
            }

            if (filter.getType() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("type"), filter.getType()));
            }

            if (filter.getStatus() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("status"), filter.getStatus()));
            }

            if (filter.getCategory() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("category").get("name"), filter.getCategory()));
            }

            if (filter.getFromAccountNumber() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("fromAccountNumber"), filter.getFromAccountNumber()));
            }

            if (filter.getToAccountNumber() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("toAccountNumber"), filter.getToAccountNumber()));
            }

            if (filter.getFrom() != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("recordDate"), filter.getFrom()));
            }

            if (filter.getTo() != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("recordDate"), filter.getTo()));
            }

            predicate = cb.and(predicate, cb.isFalse(root.get("deleted")));
            return predicate;
        };
    }

    public PageResponse<ResponseTransactionDto> listTransactionByFilter(
            TransactionFilter filter,
            Integer page,
            Integer size
    ) {
        Specification<Transactions> spec = buildSpecification(filter);

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        Page<Transactions> txnPage = transactionRepository.findAll(spec, pageable);

        return PageResponse.<ResponseTransactionDto>builder()
                .content(txnPage.map(TransactionDtoMapper::toResponseDto).getContent())
                .page(txnPage.getNumber())
                .size(txnPage.getSize())
                .totalElements(txnPage.getTotalElements())
                .totalPages(txnPage.getTotalPages())
                .build();
    }

    public ResponseTransactionDto getById(Long id) {
        Transactions txn = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction not found"));
        if (Boolean.TRUE.equals(txn.getDeleted())) {
            throw new NotFoundException("Transaction not found");
        }
        return TransactionDtoMapper.toResponseDto(txn);
    }

    public ResponseTransactionDto create(@Valid CreateTransactionDto request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));
        Transactions txn = TransactionDtoMapper.toEntity(request, category, null);
        return TransactionDtoMapper.toResponseDto(transactionRepository.save(txn));
    }

    public ResponseTransactionDto update(Long id, @Valid CreateTransactionDto request) {
        Transactions txn = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction not found"));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));
        TransactionDtoMapper.updateEntity(txn, request, category);
        return TransactionDtoMapper.toResponseDto(transactionRepository.save(txn));
    }

    public void softDelete(Long id) {
        Transactions txn = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction not found"));
        txn.setDeleted(true);
        transactionRepository.save(txn);
    }
}