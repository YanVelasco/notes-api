package com.yanvelasco.notesapi.domain.notes.service;

import com.yanvelasco.notesapi.domain.notes.dto.NoteResponseDTO;
import com.yanvelasco.notesapi.domain.notes.entity.LinkEntity;
import com.yanvelasco.notesapi.domain.notes.entity.NoteEntity;
import com.yanvelasco.notesapi.domain.notes.entity.TagEntity;
import com.yanvelasco.notesapi.domain.notes.repository.NoteRepository;
import com.yanvelasco.notesapi.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetNoteByIdService {

    private final NoteRepository noteRepository;

    public ResponseEntity<NoteResponseDTO> getNoteById(String noteId) {
        NoteEntity note = noteRepository.findById(noteId)
                .orElseThrow(() -> new NotFoundException("Note not found"));

        var noteResponseDTO = new NoteResponseDTO(
                note.getId(),
                note.getTitle(),
                note.getDescription(),
                note.getUser().getId(),
                note.getLinks().stream().map(LinkEntity::getUrl).toList(),
                note.getTags().stream().map(TagEntity::getName).toList(),
                note.getCreatedAt(),
                note.getUpdatedAt()
        );

        return ResponseEntity.ok(noteResponseDTO);
    }
}