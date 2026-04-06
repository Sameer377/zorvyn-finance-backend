package com.zorvyn.xpensify.modules.transaction;

import com.zorvyn.xpensify.modules.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */


public interface TransactionRepository extends JpaRepository<Transactions,Long>, JpaSpecificationExecutor<Transactions> {

}
