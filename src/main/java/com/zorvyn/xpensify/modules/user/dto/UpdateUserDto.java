package com.zorvyn.xpensify.modules.user.dto;

import com.zorvyn.xpensify.core.enums.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */

@ToString
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto extends CommonUserDto {

    private Role role;
}
