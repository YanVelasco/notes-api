package com.yanvelasco.notesapi.domain.notes.repository;

import com.yanvelasco.notesapi.domain.notes.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<NoteEntity, String> {
    boolean existsByTitle(String title);

    List<NoteEntity> findByUserIdOrderByTitleAsc(String userId);

    List<NoteEntity> findByUserIdAndTitleContainingIgnoreCaseOrderByTitleAsc(String userId, String title);
}