package com.zorvyn.xpensify.modules.user;

import com.zorvyn.xpensify.core.enums.Role;
import com.zorvyn.xpensify.exception.NotFoundException;
import com.zorvyn.xpensify.modules.user.dto.UpdateUserDto;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sameer Shaikh
 * @date 05-04-2026
 * @description
 */

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Specification<User> getUserSpecification(UserFilter filter){

        Specification<User> specs = (root,query,cb)->
        {
            Predicate predicate = cb.conjunction();

            if (filter.getUserId() != null) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("id"), filter.getUserId()));
            }

            if (filter.getFirstName() != null) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("firstName")),
                                "%" + filter.getFirstName().toLowerCase() + "%"));
            }

            if (filter.getLastName() != null) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("lastName")),
                                "%" + filter.getLastName().toLowerCase() + "%"));
            }

            if (filter.getEmail() != null) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("email"), filter.getEmail()));
            }

            if (filter.getCountryCode() != null) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("countryCode"), filter.getCountryCode()));
            }

            if (filter.getPhoneNumber() != null) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("phoneNumber"), filter.getPhoneNumber()));
            }

            if (filter.getGender() != null) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("gender"), filter.getGender()));
            }

            if (filter.getRole() != null) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("role"), filter.getRole()));
            }

            if (filter.getIsActive() != null) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("isActive"), filter.getIsActive()));
            }

            return predicate;
        };

        return Specification.where(specs);
    }

    @Override
    public User createUser(UpdateUserDto user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }

        User userEntity = UserDtoMapper.toEntity(user);

        return userRepository.save(userEntity);
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        if(Boolean.TRUE.equals(user.getDeleted()))
            throw new NotFoundException("User not found with id: " + id);

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, UpdateUserDto updatedUser) {
        User existing = getUserById(id);
        UserDtoMapper.updateEntity(existing, updatedUser);
        return userRepository.save(existing);
    }

    @Override
    public void deleteUser(Long id) {
        User existing = getUserById(id);
        existing.setIsActive(false);
        userRepository.save(existing);
    }

    @Override
    public User updateStatus(Long id, Boolean isActive) {
        User user = getUserById(id);
        user.setIsActive(isActive);
        User response = userRepository.save(user);
        return response;
    }

    @Override
    public User changeUserRole(Long id, Role role) {
        User existing = getUserById(id);
        existing.setRole(role);
        return userRepository.save(existing);
    }

    @Override
    public Page<User> listUsersWithFilter(UserFilter filter, Integer page, Integer size){

        Specification<User> spec = getUserSpecification(filter);
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return userRepository.findAll(spec, pageable);
    }
}
