package com.zorvyn.xpensify.modules.dashboard;

import com.zorvyn.xpensify.core.enums.TransactionType;
import com.zorvyn.xpensify.modules.transaction.TransactionRepository;
import com.zorvyn.xpensify.modules.transaction.TransactionService;
import com.zorvyn.xpensify.modules.transaction.dto.ResponseTransactionDto;
import com.zorvyn.xpensify.modules.transaction.dto.TransactionFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DashboardService {

    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    public Double getTotalIncome(Long userId) {
        return transactionRepository.getTotalIncome(userId);
    }

    public Double getTotalExpense(Long userId) {
        return transactionRepository.getTotalExpense(userId);
    }

    public Double getNetBalance(Long userId) {
        return getTotalIncome(userId) - getTotalExpense(userId);
    }

    public Map<String, Double> getCategorySummary(Long userId) {
        List<Object[]> result = transactionRepository.getCategorySummary(userId);

        Map<String, Double> map = new HashMap<>();
        for (Object[] row : result) {
            String category = (String) row[0];
            Double total = (Double) row[1];
            map.put(category, total);
        }
        return map;
    }

    public Map<String, Map<String, Double>> getMonthlySummary(Long userId) {
        List<Object[]> result = transactionRepository.getMonthlySummary(userId);

        Map<String, Map<String, Double>> response = new HashMap<>();

        for (Object[] row : result) {
            Integer month = (Integer) row[0];
            String type = row[1].toString();
            Double total = (Double) row[2];

            String monthName = Month.of(month).name();

            response
                    .computeIfAbsent(monthName, k -> new HashMap<>())
                    .put(type, total);
        }

        return response;
    }

    public List<ResponseTransactionDto> getRecentTransactions(Long userId) {
        TransactionFilter filter = TransactionFilter.builder()
                .userId(userId)
                .build();

        return transactionService
                .listTransactionByFilter(filter, 0, 5)
                .getContent();
    }
}