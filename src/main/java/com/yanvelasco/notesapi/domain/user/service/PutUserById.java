package com.yanvelasco.notesapi.domain.user.service;

import com.yanvelasco.notesapi.domain.user.dto.UserRequestDTO;
import com.yanvelasco.notesapi.domain.user.dto.UserResponseDTO;
import com.yanvelasco.notesapi.domain.user.repository.UserRepository;
import com.yanvelasco.notesapi.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PutUserById {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<UserResponseDTO> update(String id, UserRequestDTO userRequestDTO) {
        var user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        if (userRequestDTO.name() != null) {
            user.setName(userRequestDTO.name());
        }
        if (userRequestDTO.email() != null) {
            user.setEmail(userRequestDTO.email());
        }
        if (userRequestDTO.password() != null) {
            user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
        }
        if (userRequestDTO.avatarUrl() != null) {
            user.setAvatarUrl(userRequestDTO.avatarUrl());
        }

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(
                new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getAvatarUrl(),
                        user.getCreatedAt().toString(),
                        user.getUpdatedAt().toString()
                )
        );
    }
}
