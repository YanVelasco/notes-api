package com.yanvelasco.notes_api.model.notes.controller;

import com.yanvelasco.notes_api.model.notes.dto.NoteDTO;
import com.yanvelasco.notes_api.model.notes.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<NoteDTO> create(@RequestBody String content,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return noteService.creteNoteForUser(content, username);
    }

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getAll(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return noteService.getNotesForUser(username);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDTO> update(@PathVariable UUID id, @RequestBody String content,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return noteService.updateNoteForUser(id, content, username);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return noteService.deleteNoteForUser(id, username);
    }

}