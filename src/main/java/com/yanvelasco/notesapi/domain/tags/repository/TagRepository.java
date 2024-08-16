package com.yanvelasco.notesapi.domain.tags.repository;

import com.yanvelasco.notesapi.domain.tags.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, String> {
}
