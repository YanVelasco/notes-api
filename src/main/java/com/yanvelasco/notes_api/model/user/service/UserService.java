package com.yanvelasco.notes_api.model.user.service;

import com.yanvelasco.notes_api.model.user.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
    ResponseEntity<UserDTO> updateUserRole(UUID userId, String newRoleName);
    ResponseEntity<UserDTO> getUserById(UUID userId);
    ResponseEntity<List<UserDTO>> getAllUsers();
}
