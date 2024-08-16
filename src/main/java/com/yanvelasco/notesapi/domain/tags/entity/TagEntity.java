package com.yanvelasco.notesapi.domain.tags.entity;

import com.yanvelasco.notesapi.domain.notes.entity.NoteEntity;
import com.yanvelasco.notesapi.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notes_tags")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private String id;

    @Column(name = "tag_name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "note_id")
    private NoteEntity noteId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity userId;
}