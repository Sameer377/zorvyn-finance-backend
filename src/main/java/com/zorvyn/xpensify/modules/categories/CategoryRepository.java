package com.zorvyn.xpensify.modules.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
