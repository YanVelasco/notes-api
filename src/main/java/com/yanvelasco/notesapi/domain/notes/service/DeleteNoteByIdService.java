package com.yanvelasco.notesapi.domain.notes.service;

import com.yanvelasco.notesapi.domain.notes.repository.NoteRepository;
import com.yanvelasco.notesapi.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteNoteByIdService {

    private final NoteRepository noteRepository;

    public ResponseEntity<Void> deleteNoteById(String noteId) {
        if (!noteRepository.existsById(noteId)) {
            throw new NotFoundException("Note not found");
        }

        noteRepository.deleteById(noteId);
        return ResponseEntity.noContent().build();
    }
}