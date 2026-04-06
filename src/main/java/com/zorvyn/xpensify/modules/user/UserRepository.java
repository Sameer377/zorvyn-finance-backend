package com.zorvyn.xpensify.modules.user;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Sameer Shaikh
 * @date 05-04-2026
 * @description
 */

public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    Boolean existsByEmail(String email);

    User findByEmail(String email);

}
