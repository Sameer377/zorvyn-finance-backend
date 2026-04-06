package com.zorvyn.xpensify.config;

import com.zorvyn.xpensify.core.enums.Role;
import com.zorvyn.xpensify.modules.user.User;
import com.zorvyn.xpensify.modules.user.UserService;
import com.zorvyn.xpensify.modules.user.dto.CreateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        String adminEmail = "admin@gmail.com";

        if (!userService.existsByEmail(adminEmail)) {

            CreateUserDto admin = CreateUserDto.builder()
                    .firstName("Default Admin")
                    .email(adminEmail)
                    .password("admin@123")
                    .role(Role.ROLE_ADMIN)
                    .build();

            userService.createUser(admin);

            System.out.println("Default admin created");
        }
    }
}
