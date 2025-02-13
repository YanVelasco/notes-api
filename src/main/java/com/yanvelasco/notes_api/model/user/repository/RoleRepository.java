package com.yanvelasco.notes_api.model.user.repository;

import com.yanvelasco.notes_api.model.user.entities.AppRole;
import com.yanvelasco.notes_api.model.user.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByRoleName(AppRole roleName);
}