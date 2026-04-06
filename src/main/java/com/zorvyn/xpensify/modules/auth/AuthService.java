package com.zorvyn.xpensify.modules.auth;

import com.zorvyn.xpensify.config.JwtUtil;
import com.zorvyn.xpensify.core.enums.Role;
import com.zorvyn.xpensify.exception.AuthException;
import com.zorvyn.xpensify.modules.user.UserService;
import com.zorvyn.xpensify.modules.user.dto.CreateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Sameer Shaikh
 * @date 04-04-2026
 * @description
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public String login(String email, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        UserDetails userDetails =
                userService.loadUserByUsername(email);

        return jwtUtil.generateToken(userDetails);
    }

}