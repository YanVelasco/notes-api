package com.yanvelasco.notesapi.domain.notes.service;

import com.yanvelasco.notesapi.domain.notes.dto.NoteRequestDTO;
import com.yanvelasco.notesapi.domain.notes.dto.NoteResponseDTO;
import com.yanvelasco.notesapi.domain.notes.entity.LinkEntity;
import com.yanvelasco.notesapi.domain.notes.entity.NoteEntity;
import com.yanvelasco.notesapi.domain.notes.entity.TagEntity;
import com.yanvelasco.notesapi.domain.notes.repository.NoteRepository;
import com.yanvelasco.notesapi.domain.notes.repository.TagRepository;
import com.yanvelasco.notesapi.domain.user.repository.UserRepository;
import com.yanvelasco.notesapi.exceptions.NotFoundException;
import com.yanvelasco.notesapi.exceptions.NoteAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateNoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    public ResponseEntity<NoteResponseDTO> createNote(String userId, NoteRequestDTO noteRequestDTO) {

        var user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        var newNote = NoteEntity.builder()
                .title(noteRequestDTO.title())
                .description(noteRequestDTO.description())
                .user(user.get())
                .build();

        if (noteRepository.existsByTitle(newNote.getTitle())) {
            throw new NoteAlreadyExistsException("Note with title " + newNote.getTitle() + " already exists");
        }

        List<LinkEntity> links = new ArrayList<>();
        for (String url : noteRequestDTO.links()) {
            LinkEntity linkEntity = new LinkEntity(null, url, newNote);
            links.add(linkEntity);
        }
        newNote.setLinks(links);

        List<TagEntity> tags = new ArrayList<>();
        for (String tagName : noteRequestDTO.tags()) {
            TagEntity tagEntity = tagRepository.findByName(tagName)
                    .orElseGet(() -> tagRepository.save(new TagEntity(tagName)));
            tags.add(tagEntity);
        }
        newNote.setTags(tags);

        var savedNote = noteRepository.save(newNote);

        var noteResponseDTO = new NoteResponseDTO(
                savedNote.getId(),
                savedNote.getTitle(),
                savedNote.getDescription(),
                savedNote.getUser().getId(),
                savedNote.getLinks().stream().map(LinkEntity::getUrl).toList(),
                savedNote.getTags().stream().map(TagEntity::getName).toList(),
                savedNote.getCreatedAt(),
                savedNote.getUpdatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(noteResponseDTO);
    }
}