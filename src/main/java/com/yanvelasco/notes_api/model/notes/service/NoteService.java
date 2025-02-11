package com.yanvelasco.notes_api.model.notes.service;

import com.yanvelasco.notes_api.model.notes.dto.NoteDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface NoteService {
    ResponseEntity<NoteDTO> creteNoteForUser(String username, String content);
    ResponseEntity<List<NoteDTO>> getNotesForUser(String ownerUsername);
    ResponseEntity<NoteDTO> updateNoteForUser(UUID noteId, String content, String username);
    ResponseEntity<Void> deleteNoteForUser(UUID noteId, String username);
}
