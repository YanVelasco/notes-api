package com.yanvelasco.notesapi.domain.links.service;

import com.yanvelasco.notesapi.domain.links.dto.LinkRequestDTO;
import com.yanvelasco.notesapi.domain.links.dto.LinkResponseDTO;
import com.yanvelasco.notesapi.domain.links.entity.LinkEntity;
import com.yanvelasco.notesapi.domain.links.repository.LinkRepository;
import com.yanvelasco.notesapi.domain.notes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateLinkService {

    private final NoteRepository noteRepository;
    private final LinkRepository linkRepository;

    public ResponseEntity<LinkResponseDTO> createLink(String noteId, LinkRequestDTO linkRequestDTO) {
        var note = noteRepository.findById(noteId);

        if (note.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var noteEntity = note.get();

        var link = LinkEntity.builder()
                .url(linkRequestDTO.url())
                .noteId(noteEntity)
                .build();

        linkRepository.save(link);

        var response = new LinkResponseDTO(link.getId(), link.getUrl(), link.getNoteId().getId(), link.getCreatedAt().toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}