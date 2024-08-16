package com.yanvelasco.notesapi.domain.notes.dto;

import java.sql.Timestamp;

public record NoteResponseDTO(
        String id,
        String title,
        String description,
        String userId,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}
