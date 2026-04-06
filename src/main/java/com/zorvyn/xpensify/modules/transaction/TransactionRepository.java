package com.zorvyn.xpensify.modules.transaction;

import com.zorvyn.xpensify.modules.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */


public interface TransactionRepository extends JpaRepository<Transactions,Long>, JpaSpecificationExecutor<Transactions> {
    @Query("""
    SELECT COALESCE(SUM(t.amount), 0)
    FROM Transactions t
    WHERE t.user.id = :userId
    AND t.type = 'INCOME'
    AND t.deleted = false
""")
    Double getTotalIncome(Long userId);

    @Query("""
    SELECT COALESCE(SUM(t.amount), 0)
    FROM Transactions t
    WHERE t.user.id = :userId
    AND t.type = 'EXPENSE'
    AND t.deleted = false
""")
    Double getTotalExpense(Long userId);

    @Query("""
    SELECT t.category.name, SUM(t.amount)
    FROM Transactions t
    WHERE t.user.id = :userId
    AND t.deleted = false
    GROUP BY t.category.name
""")
    List<Object[]> getCategorySummary(Long userId);

    @Query("""
    SELECT FUNCTION('MONTH', t.recordDate),
           t.type,
           SUM(t.amount)
    FROM Transactions t
    WHERE t.user.id = :userId
    AND t.deleted = false
    GROUP BY FUNCTION('MONTH', t.recordDate), t.type
""")
    List<Object[]> getMonthlySummary(Long userId);
}
