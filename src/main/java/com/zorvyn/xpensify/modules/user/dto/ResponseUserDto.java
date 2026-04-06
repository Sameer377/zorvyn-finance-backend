package com.zorvyn.xpensify.modules.user.dto;

import com.zorvyn.xpensify.core.enums.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author Sameer Shaikh
 * @date 05-04-2026
 * @description
 */

@ToString
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDto extends CommonUserDto {

    private Long id;
    private Role role;
    private Boolean isActive;
}
