package com.yanvelasco.notesapi.domain.user.service;

import com.yanvelasco.notesapi.domain.user.dto.UserRequestDTO;
import com.yanvelasco.notesapi.domain.user.dto.UserResponseDTO;
import com.yanvelasco.notesapi.domain.user.entity.UserEntity;
import com.yanvelasco.notesapi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<UserResponseDTO> create(UserRequestDTO userRequestDTO) {
        var encryptedPassword = passwordEncoder.encode(userRequestDTO.password());

        var newUser = UserEntity.builder()
                .name(userRequestDTO.name())
                .email(userRequestDTO.email())
                .password(encryptedPassword)
                .avatarUrl(userRequestDTO.avatarUrl())
                .updatedAt(null)
                .build();

        if (userRepository.existsByEmail(newUser.getEmail())) {
            return ResponseEntity.badRequest().build();
        }

        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new UserResponseDTO(
                        newUser.getId(),
                        newUser.getName(),
                        newUser.getEmail(),
                        newUser.getAvatarUrl(),
                        newUser.getCreatedAt().toString(),
                        newUser.getUpdatedAt().toString()
                )
        );
    }
}