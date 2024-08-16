package com.yanvelasco.notesapi.domain.tags.dto;

public record TagResponseDTO(
        String id,
        String name,
        String noteId,
        String userId
) {
}
