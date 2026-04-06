package com.zorvyn.xpensify.modules.user.dto;

/**
 * @author Sameer Shaikh
 * @date 05-04-2026
 * @description
 */
import com.zorvyn.xpensify.core.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CommonUserDto {

        @Size(max = 50, message = "First name must be less than 50 characters")
        private String firstName;

        @Size(max = 50, message = "Last name must be less than 50 characters")
        private String lastName;

        @Email(message = "Invalid email format")
        private String email;

        @Pattern(regexp = "^\\+[1-9]\\d{1,3}$", message = "Country code must be valid (e.g. +91, +1)")
        private String countryCode;

        @Pattern(regexp = "^\\d{7,15}$", message = "Phone number must be between 7 and 15 digits")
        private String phoneNumber;

        @NotNull(message = "Gender is required")
        private Gender gender;
}
