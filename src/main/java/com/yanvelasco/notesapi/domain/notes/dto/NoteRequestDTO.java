package com.yanvelasco.notesapi.domain.notes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record NoteRequestDTO(
        @NotBlank(message = "Title is required") String title,
        @NotBlank(message = "Description is required") String description,
        @NotNull(message = "Links are required") List<String> links,
        @NotNull(message = "Tags are required") List<String> tags
) {
}