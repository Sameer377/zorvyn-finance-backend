package com.zorvyn.xpensify.modules.user;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */


import com.zorvyn.xpensify.core.enums.Gender;
import com.zorvyn.xpensify.core.enums.Role;
import com.zorvyn.xpensify.modules.user.dto.UpdateUserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Controller")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @Operation(summary = "Endpoint to create a new user")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UpdateUserDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @Operation(summary = "Endpoint to get user by ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @Operation(summary = "Endpoint to update user by ID")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserDto dto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, dto));
    }

    @Operation(summary = "Endpoint to delete user by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Endpoint to update user active status")
    @PatchMapping("/{id}/status")
    public ResponseEntity<User> updateStatus(
            @PathVariable Long id,
            @RequestParam Boolean isActive
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateStatus(id, isActive));
    }

    @Operation(summary = "Endpoint to change user role")
    @PatchMapping("/{id}/role")
    public ResponseEntity<User> changeRole(
            @PathVariable Long id,
            @RequestParam Role role
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.changeUserRole(id, role));
    }

    @Operation(summary = "Endpoint to get paginated list of users with optional filters")
    @GetMapping
    public ResponseEntity<Page<User>> getUsers(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String countryCode,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) Role role,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        UserFilter filter = new UserFilter();
        filter.setUserId(userId);
        filter.setFirstName(firstName);
        filter.setLastName(lastName);
        filter.setEmail(email);
        filter.setCountryCode(countryCode);
        filter.setPhoneNumber(phoneNumber);
        filter.setGender(gender);
        filter.setRole(role);
        filter.setIsActive(isActive);

        return ResponseEntity.status(HttpStatus.OK).body(userService.listUsersWithFilter(filter, page, size));
    }

}