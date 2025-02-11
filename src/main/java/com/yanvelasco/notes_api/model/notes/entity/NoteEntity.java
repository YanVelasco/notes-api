package com.yanvelasco.notes_api.model.notes.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "content", nullable = false, length = 5000)
    private String content;

    @Column(name = "owner_username", nullable = false)
    private String ownerUsername;

}
