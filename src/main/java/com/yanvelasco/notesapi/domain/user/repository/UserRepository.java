package com.yanvelasco.notesapi.domain.user.repository;

import com.yanvelasco.notesapi.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByEmail(String email);

    UserDetails findByEmail(String email);
}