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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllNotesByUserService {

    private final NoteRepository noteRepository;

    public List<ResponseEntity<NoteResponseDTO>> getNotesByUser(String userId, String title) {
        List<NoteEntity> notes = noteRepository.findByUserIdAndTitleContainingIgnoreCaseOrderByTitleAsc(userId, title);
        if (notes.isEmpty()) {
            throw new NotFoundException("No notes found for the user");
        }
        List<ResponseEntity<NoteResponseDTO>> list = new ArrayList<>();
        for (NoteEntity note : notes) {
            NoteResponseDTO noteResponseDTO = new NoteResponseDTO(
                    note.getId(),
                    note.getTitle(),
                    note.getDescription(),
                    note.getUser().getId(),
                    note.getLinks().stream().map(LinkEntity::getUrl).toList(),
                    note.getTags().stream().map(TagEntity::getName).toList(),
                    note.getCreatedAt(),
                    note.getUpdatedAt()
            );
            list.add(ResponseEntity.ok(noteResponseDTO));
        }
        return list;
    }
}