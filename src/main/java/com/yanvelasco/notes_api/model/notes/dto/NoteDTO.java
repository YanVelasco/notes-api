package com.yanvelasco.notes_api.model.notes.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record NoteDTO(
        UUID id,
        String content,
        String ownerUsername
) {
}
