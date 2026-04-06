package com.zorvyn.xpensify.modules.user;

import com.zorvyn.xpensify.core.enums.Gender;
import com.zorvyn.xpensify.core.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFilter {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String countryCode;
    private String phoneNumber;
    private Gender gender;
    private Role role;
    private Boolean isActive;
}
