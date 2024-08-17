package com.yanvelasco.notesapi.domain.notes.repository;

import com.yanvelasco.notesapi.domain.notes.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, String> {
    Optional<TagEntity> findByName(String tagName);
}
