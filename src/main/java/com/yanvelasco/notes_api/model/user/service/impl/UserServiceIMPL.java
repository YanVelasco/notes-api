package com.yanvelasco.notes_api.model.user.service.impl;

import com.yanvelasco.notes_api.exceptions.IsEmptyException;
import com.yanvelasco.notes_api.exceptions.ResourceNotFoundException;
import com.yanvelasco.notes_api.model.user.dto.UserDTO;
import com.yanvelasco.notes_api.model.user.entities.AppRole;
import com.yanvelasco.notes_api.model.user.entities.RoleEntity;
import com.yanvelasco.notes_api.model.user.entities.UserEntity;
import com.yanvelasco.notes_api.model.user.repository.RoleRepository;
import com.yanvelasco.notes_api.model.user.repository.UserRepository;
import com.yanvelasco.notes_api.model.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceIMPL implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceIMPL(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserEntity findUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "Id", userId)
        );
    }

    @Override
    public ResponseEntity<UserDTO> updateUserRole(UUID userId, String newRoleName) {
        UserEntity user = findUserById(userId);
        AppRole appRole = AppRole.valueOf(newRoleName);
        RoleEntity role = roleRepository.findByRoleName(appRole)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "roleName", newRoleName));
        user.setRole(role);
        userRepository.save(user);
        return getUserDTOResponseEntity(user);
    }

    @Override
    public ResponseEntity<UserDTO> getUserById(UUID userId) {
        UserEntity user = findUserById(userId);
        return getUserDTOResponseEntity(user);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new IsEmptyException("Users not found");
        }

        List<UserDTO> userDTOs = users.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }

    private ResponseEntity<UserDTO> getUserDTOResponseEntity(UserEntity user) {
        return ResponseEntity.ok(convertToUserDTO(user));
    }

    private UserDTO convertToUserDTO(UserEntity user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .accountNonExpired(user.getAccountNonExpired())
                .accountNonLocked(user.getAccountNonLocked())
                .credentialsNonExpired(user.getCredentialsNonExpired())
                .enabled(user.getEnabled())
                .credentialsNonExpiryDate(user.getCredentialsNonExpiryDate())
                .accountExpiryDate(user.getAccountExpiryDate())
                .twoFactorSecret(user.getTwoFactorSecret())
                .twoFactorEnabled(user.getTwoFactorEnabled())
                .signUpMethod(user.getSignUpMethod())
                .roleEntity(
                        RoleEntity.builder()
                                .id(user.getRole().getId())
                                .roleName(user.getRole().getRoleName())
                                .build()
                )
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}