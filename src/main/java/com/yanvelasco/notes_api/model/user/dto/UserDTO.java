package com.yanvelasco.notes_api.model.user.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record UserDTO(
    UUID id,
    String username,
    String email,
    Boolean accountNonExpired,
    Boolean accountNonLocked,
    Boolean credentialsNonExpired,
    Boolean enabled,
    LocalDateTime credentialsNonExpiryDate,
    LocalDateTime accountExpiryDate,
    String twoFactorSecret,
    Boolean twoFactorEnabled,
    String signUpMethod,
    UUID roleId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}