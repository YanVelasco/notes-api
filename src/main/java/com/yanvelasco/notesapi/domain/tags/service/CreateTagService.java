package com.yanvelasco.notesapi.domain.tags.service;

import com.yanvelasco.notesapi.domain.notes.repository.NoteRepository;
import com.yanvelasco.notesapi.domain.tags.dto.TagRequestDTO;
import com.yanvelasco.notesapi.domain.tags.dto.TagResponseDTO;
import com.yanvelasco.notesapi.domain.tags.entity.TagEntity;
import com.yanvelasco.notesapi.domain.tags.repository.TagRepository;
import com.yanvelasco.notesapi.domain.user.repository.UserRepository;
import com.yanvelasco.notesapi.domain.notes.entity.NoteEntity;
import com.yanvelasco.notesapi.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTagService {

    private final TagRepository tagRepository;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public ResponseEntity<TagResponseDTO> createTag(String noteId, String userId, TagRequestDTO tagRequestDTO) {
        var note = noteRepository.findById(noteId);
        var user = userRepository.findById(userId);

        if (note.isEmpty() || user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        NoteEntity noteEntity = note.get();
        UserEntity userEntity = user.get();

        var tag = TagEntity.builder()
                .name(tagRequestDTO.name())
                .noteId(noteEntity)
                .userId(userEntity)
                .build();

        tagRepository.save(tag);

        var response = new TagResponseDTO(tag.getId(), tag.getName(), tag.getNoteId().getId(), tag.getUserId().getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}