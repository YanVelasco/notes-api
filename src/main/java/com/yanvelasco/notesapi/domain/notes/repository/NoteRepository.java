package com.yanvelasco.notesapi.domain.notes.repository;

import com.yanvelasco.notesapi.domain.notes.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<NoteEntity, String> {
    boolean existsByTitle(String title);
}
