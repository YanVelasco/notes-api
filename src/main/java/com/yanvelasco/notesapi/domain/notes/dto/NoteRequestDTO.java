package com.yanvelasco.notesapi.domain.notes.dto;

import jakarta.validation.constraints.NotBlank;

public record NoteRequestDTO(
        @NotBlank(message = "Title is required") String title,
        @NotBlank(message = "Description is required") String description
) {
}
