package com.zorvyn.xpensify.modules.user.dto;

import com.zorvyn.xpensify.core.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */
public class CreateUserDto extends CommonUserDto {

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    private Role role;
}
