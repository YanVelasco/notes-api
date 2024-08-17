package com.yanvelasco.notesapi.domain.notes.controller;

import com.yanvelasco.notesapi.domain.notes.entity.TagEntity;
import com.yanvelasco.notesapi.domain.notes.service.GetAllTagsByUserIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private final GetAllTagsByUserIdService getAllTagsByUserIdService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<TagEntity>> getAllTags(@PathVariable String userId) {
        return getAllTagsByUserIdService.getAllTagsByUser(userId);
    }

}
