package com.yanvelasco.notesapi.domain.tags.controller;

import com.yanvelasco.notesapi.domain.tags.dto.TagRequestDTO;
import com.yanvelasco.notesapi.domain.tags.dto.TagResponseDTO;
import com.yanvelasco.notesapi.domain.tags.service.CreateTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private final CreateTagService createTagService;

    @PostMapping
    public ResponseEntity<TagResponseDTO> createTag(@RequestParam String noteId, @RequestParam String userId, @RequestBody TagRequestDTO tagRequestDTO) {
        return createTagService.createTag(noteId, userId, tagRequestDTO);
    }
}