package com.yanvelasco.notesapi.domain.notes.dto;

import java.sql.Timestamp;
import java.util.List;

public record NoteResponseDTO(
        String id,
        String title,
        String description,
        String userId,
        List<String> links,
        List<String> tags,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}