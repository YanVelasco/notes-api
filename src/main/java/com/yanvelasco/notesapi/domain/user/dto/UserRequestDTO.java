package com.yanvelasco.notesapi.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
       @NotBlank(message = "Name is required") String name,
       @NotBlank(message = "Email is required") String email,
       @NotBlank(message = "Password is required") String password,
       String avatarUrl
) {
}