package com.yanvelasco.notes_api.model.notes.repository;

import com.yanvelasco.notes_api.model.notes.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<NoteEntity, UUID> {
    List<NoteEntity> findAllByOwnerUsername(String ownerUsername);
}
