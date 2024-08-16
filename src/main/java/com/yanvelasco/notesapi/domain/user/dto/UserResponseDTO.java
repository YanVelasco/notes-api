package com.yanvelasco.notesapi.domain.user.dto;

public record UserResponseDTO(
        String id,
        String name,
        String email,
        String avatarUrl,
        String createdAt,
        String updatedAt
) {
}
