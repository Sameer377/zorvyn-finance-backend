package com.zorvyn.xpensify.modules.user;

import com.zorvyn.xpensify.core.PageResponse;
import com.zorvyn.xpensify.core.enums.Role;
import com.zorvyn.xpensify.modules.user.dto.CreateUserDto;
import com.zorvyn.xpensify.modules.user.dto.ResponseUserDto;
import com.zorvyn.xpensify.modules.user.dto.UpdateUserDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author Sameer Shaikh
 * @date 05-04-2026
 * @description
 */

public interface UserService extends UserDetailsService {

    User changeUserRole(Long id, Role role);

    User createUser(CreateUserDto user);

    User getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(Long id, UpdateUserDto updatedUser);

    void deleteUser(Long id);

    User updateStatus(Long id,Boolean isActive);

    PageResponse<ResponseUserDto> listUsersWithFilter(UserFilter filter, Integer page, Integer size);

    boolean existsByEmail(String email);
}
