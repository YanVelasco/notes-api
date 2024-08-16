package com.yanvelasco.notesapi.domain.notes.controller;

import com.yanvelasco.notesapi.domain.notes.dto.NoteRequestDTO;
import com.yanvelasco.notesapi.domain.notes.dto.NoteResponseDTO;
import com.yanvelasco.notesapi.domain.notes.service.CreateNoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final CreateNoteService createNoteService;

    @PostMapping("/{userId}")
    public ResponseEntity<NoteResponseDTO> createNote(@PathVariable String userId, @RequestBody @Valid NoteRequestDTO noteRequestDTO) {
        return createNoteService.createNote(userId,noteRequestDTO);
    }

}