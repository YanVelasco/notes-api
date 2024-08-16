package com.yanvelasco.notesapi.domain.notes.service;

import com.yanvelasco.notesapi.domain.notes.dto.NoteRequestDTO;
import com.yanvelasco.notesapi.domain.notes.dto.NoteResponseDTO;
import com.yanvelasco.notesapi.domain.notes.entity.NoteEntity;
import com.yanvelasco.notesapi.domain.notes.repository.NoteRepository;
import com.yanvelasco.notesapi.domain.user.repository.UserRepository;
import com.yanvelasco.notesapi.exceptions.NotFoundException;
import com.yanvelasco.notesapi.exceptions.NoteAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public ResponseEntity<NoteResponseDTO> createNote(String userId, NoteRequestDTO noteRequestDTO) {

        var user = userRepository.findById(String.valueOf(userId));
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        var newNote = NoteEntity.builder()
                .title(noteRequestDTO.title())
                .description(noteRequestDTO.description())
                .userId(user.get())
                .build();

        if (noteRepository.existsByTitle(newNote.getTitle())) {
            throw new NoteAlreadyExistsException("Note with title " + newNote.getTitle() + " already exists");
        }

        var savedNote = noteRepository.save(newNote);

        var noteResponseDTO = new NoteResponseDTO(
                savedNote.getId(),
                savedNote.getTitle(),
                savedNote.getDescription(),
                savedNote.getUserId().getId(),
                savedNote.getCreatedAt(),
                savedNote.getUpdatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(noteResponseDTO);
    }
}