package com.zorvyn.xpensify.modules.dashboard;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */


import com.zorvyn.xpensify.modules.transaction.dto.ResponseTransactionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Financial dashboard analytics")
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(summary = "Endpoint to get total income")
    @GetMapping("/total-income")
    public ResponseEntity<Double> getTotalIncome(@RequestParam Long userId) {
        return ResponseEntity.ok(dashboardService.getTotalIncome(userId));
    }

    @Operation(summary = "Endpoint to get total expense")
    @GetMapping("/total-expense")
    public ResponseEntity<Double> getTotalExpense(@RequestParam Long userId) {
        return ResponseEntity.ok(dashboardService.getTotalExpense(userId));
    }

    @Operation(summary = "Endpoint to get net balance")
    @GetMapping("/net-balance")
    public ResponseEntity<Double> getNetBalance(@RequestParam Long userId) {
        return ResponseEntity.ok(dashboardService.getNetBalance(userId));
    }

    @Operation(summary = "Endpoint to get category-wise summary")
    @GetMapping("/category-summary")
    public ResponseEntity<Map<String, Double>> getCategorySummary(@RequestParam Long userId) {
        return ResponseEntity.ok(dashboardService.getCategorySummary(userId));
    }

    @Operation(summary = "Endpoint to get recent transactions")
    @GetMapping("/recent")
    public ResponseEntity<List<ResponseTransactionDto>> getRecentTransactions(@RequestParam Long userId) {
        return ResponseEntity.ok(dashboardService.getRecentTransactions(userId));
    }

    @Operation(summary = "Endpoint to get monthly summary")
    @GetMapping("/monthly")
    public ResponseEntity<Map<String, Map<String, Double>>> getMonthlySummary(@RequestParam Long userId) {
        return ResponseEntity.ok(dashboardService.getMonthlySummary(userId));
    }
}