package com.yanvelasco.notesapi.domain.notes.service;

import com.yanvelasco.notesapi.domain.notes.entity.TagEntity;
import com.yanvelasco.notesapi.domain.notes.repository.TagRepository;
import com.yanvelasco.notesapi.domain.user.repository.UserRepository;
import com.yanvelasco.notesapi.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllTagsByUserIdService {

    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public ResponseEntity<List<TagEntity>> getAllTagsByUser(String userId) {
        var user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        List<TagEntity> tags = tagRepository.findByUserId(userId);
        return ResponseEntity.ok(tags);
    }
}