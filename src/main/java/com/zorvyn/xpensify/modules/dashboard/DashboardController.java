package com.zorvyn.xpensify.modules.dashboard;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Aggregated analytics endpoints — ANALYST and ADMIN access only")
public class DashboardController {

//    private final DashboardService dashboardService;

    // crunch the numbers and return the key headline figures for the dashboard
    @Operation(summary = "Endpoint to retrieve total income, total expenses, and net balance for a given period")
    @GetMapping("/summary")
    public ResponseEntity<?> getSummary(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month
    ) {
        return null; //ResponseEntity.ok(dashboardService.getSummary(year, month));
    }

    // break down the total spend and earnings into per-category slices
    @Operation(summary = "Endpoint to retrieve income and expense totals grouped by category")
    @GetMapping("/category-totals")
    public ResponseEntity<?> getCategoryTotals(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer year) {
        return null; //ResponseEntity.ok(dashboardService.getCategoryTotals(type, year));
    }

    // show how income and expenses moved month by month across the year
    @Operation(summary = "Endpoint to retrieve month-over-month income and expense trends for a given year")
    @GetMapping("/monthly-trends")
    public ResponseEntity<?> getMonthlyTrends(
            @RequestParam(required = false) Integer year,
            @RequestParam(defaultValue = "12") int months) {
        return null; //ResponseEntity.ok(dashboardService.getMonthlyTrends(year, months));
    }

    // grab the latest transactions to populate the recent activity feed
    @Operation(summary = "Endpoint to retrieve the most recent N transactions for the activity feed")
    @GetMapping("/recent")
    public ResponseEntity<?> getRecentTransactions(
            @RequestParam(defaultValue = "10") int limit) {
        return null; //ResponseEntity.ok(dashboardService.getRecentTransactions(limit));
    }
}