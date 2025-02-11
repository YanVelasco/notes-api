package com.yanvelasco.notes_api.model.notes.service.imp;

import com.yanvelasco.notes_api.exceptions.IsEmptyException;
import com.yanvelasco.notes_api.exceptions.ResourceNotFoundException;
import com.yanvelasco.notes_api.model.notes.dto.NoteDTO;
import com.yanvelasco.notes_api.model.notes.entity.NoteEntity;
import com.yanvelasco.notes_api.model.notes.repository.NoteRepository;
import com.yanvelasco.notes_api.model.notes.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NoteServiceIMP implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceIMP(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    private void findNotesId(UUID noteId) {
        noteRepository.findById(noteId).orElseThrow(
                () -> new ResourceNotFoundException("Note", "id", noteId)
        );
    }

    @Override
    public ResponseEntity<NoteDTO> creteNoteForUser(String username, String content) {
        var note = NoteEntity.builder()
                .content(content)
                .ownerUsername(username)
                .build();

        var noteSaved = noteRepository.save(note);

        return ResponseEntity.status(HttpStatus.CREATED).body(NoteDTO.builder()
                .id(noteSaved.getId())
                .content(noteSaved.getContent())
                .ownerUsername(noteSaved.getOwnerUsername())
                .build());
    }

    @Override
    public ResponseEntity<List<NoteDTO>> getNotesForUser(String ownerUsername) {
        var notes = noteRepository.findAllByOwnerUsername(ownerUsername);

        if (notes.isEmpty()) {
            throw new IsEmptyException("There are no notes for this user");
        }

        return ResponseEntity.ok(notes.stream().map(note -> NoteDTO.builder()
                .id(note.getId())
                .content(note.getContent())
                .ownerUsername(note.getOwnerUsername())
                .build()).toList());
    }

    @Override
    public ResponseEntity<NoteDTO> updateNoteForUser(UUID noteId, String content, String username) {
        findNotesId(noteId);

        var note = NoteEntity.builder()
                .id(noteId)
                .content(content)
                .ownerUsername(username)
                .build();

        var noteSaved = noteRepository.save(note);

        return ResponseEntity.ok(NoteDTO.builder()
                .id(noteSaved.getId())
                .content(noteSaved.getContent())
                .ownerUsername(noteSaved.getOwnerUsername())
                .build());
    }

    @Override
    public ResponseEntity<Void> deleteNoteForUser(UUID noteId, String username) {
        findNotesId(noteId);

        noteRepository.deleteById(noteId);

        return ResponseEntity.noContent().build();
    }
}