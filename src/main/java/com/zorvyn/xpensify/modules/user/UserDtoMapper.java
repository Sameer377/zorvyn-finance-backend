package com.zorvyn.xpensify.modules.user;

import com.zorvyn.xpensify.modules.user.dto.*;

/**
 * @author Sameer Shaikh
 * @date 05-04-2026
 * @description
 */
public class UserDtoMapper {

    // Entity to ResponseDto
    public static ResponseUserDto toResponseDto(User user) {
        if (user == null) return null;

        return ResponseUserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .countryCode(user.getCountryCode())
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender())
                .role(user.getRole())
                .isActive(user.getIsActive())
                .build();
    }

    // CommonDto to Entity (for create)
    public static User toEntity(CreateUserDto dto) {
        if (dto == null) return null;

        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .countryCode(dto.getCountryCode())
                .phoneNumber(dto.getPhoneNumber())
                .gender(dto.getGender())
                .build();
    }

    public static void updateEntity(User user, UpdateUserDto dto) {
        if (dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) user.setLastName(dto.getLastName());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getCountryCode() != null) user.setCountryCode(dto.getCountryCode());
        if (dto.getPhoneNumber() != null) user.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getGender() != null) user.setGender(dto.getGender());
    }
}