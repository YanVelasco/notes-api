package com.yanvelasco.notesapi.domain.notes.repository;

import com.yanvelasco.notesapi.domain.notes.entity.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<LinkEntity, String> {
}
