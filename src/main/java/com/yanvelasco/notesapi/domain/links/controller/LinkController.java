package com.yanvelasco.notesapi.domain.links.controller;

import com.yanvelasco.notesapi.domain.links.dto.LinkRequestDTO;
import com.yanvelasco.notesapi.domain.links.dto.LinkResponseDTO;
import com.yanvelasco.notesapi.domain.links.service.CreateLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/links")
@RequiredArgsConstructor
public class LinkController {

    private final CreateLinkService createLinkService;

    @PostMapping
    public ResponseEntity<LinkResponseDTO> createLink(@RequestParam String noteId, @RequestBody LinkRequestDTO linkRequestDTO) {
        return createLinkService.createLink(noteId, linkRequestDTO);
    }
}