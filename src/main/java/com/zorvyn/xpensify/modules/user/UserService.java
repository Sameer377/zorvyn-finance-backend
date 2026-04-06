package com.zorvyn.xpensify.modules.user;

import com.zorvyn.xpensify.core.enums.Role;
import com.zorvyn.xpensify.modules.user.dto.UpdateUserDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Sameer Shaikh
 * @date 05-04-2026
 * @description
 */

public interface UserService {

    User changeUserRole(Long id, Role role);
    User createUser(UpdateUserDto user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, UpdateUserDto updatedUser);
    void deleteUser(Long id);
    User updateStatus(Long id,Boolean isActive);
    Page<User> listUsersWithFilter(UserFilter filter, Integer page, Integer size);
}
