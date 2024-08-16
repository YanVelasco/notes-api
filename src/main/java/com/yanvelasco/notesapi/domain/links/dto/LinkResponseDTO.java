package com.yanvelasco.notesapi.domain.links.dto;

public record LinkResponseDTO(
        String id,
        String url,
        String noteId,
        String createdAt
) {
}
