package com.yanvelasco.notesapi.domain.notes.controller;

import com.yanvelasco.notesapi.domain.notes.dto.NoteRequestDTO;
import com.yanvelasco.notesapi.domain.notes.dto.NoteResponseDTO;
import com.yanvelasco.notesapi.domain.notes.service.CreateNoteService;
import com.yanvelasco.notesapi.domain.notes.service.DeleteNoteByIdService;
import com.yanvelasco.notesapi.domain.notes.service.GetAllNotesByUserService;
import com.yanvelasco.notesapi.domain.notes.service.GetNoteByIdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final CreateNoteService createNoteService;
    private final GetNoteByIdService getNoteByIdService;
    private final DeleteNoteByIdService deleteNoteByIdService;
    private final GetAllNotesByUserService getAllNotesByUserService;

    @PostMapping("/{userId}")
    public ResponseEntity<NoteResponseDTO> createNote(@PathVariable String userId, @RequestBody @Valid NoteRequestDTO noteRequestDTO) {
        return createNoteService.createNote(userId, noteRequestDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDTO> getNoteById(@PathVariable String id) {
        return getNoteByIdService.getNoteById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable String id) {
        return deleteNoteByIdService.deleteNoteById(id);
    }

    @GetMapping("/{id}/all")
    public List<ResponseEntity<NoteResponseDTO>> getAllNotes(@PathVariable String id, @RequestParam(required = false) String title) {
        return getAllNotesByUserService.getNotesByUser(id, title);
    }
}