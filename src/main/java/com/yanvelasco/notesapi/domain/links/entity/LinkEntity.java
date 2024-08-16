package com.yanvelasco.notesapi.domain.links.entity;

import com.yanvelasco.notesapi.domain.notes.entity.NoteEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "links")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private String id;

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "note_id")
    private NoteEntity noteId;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;
}
