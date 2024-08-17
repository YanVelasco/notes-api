package com.yanvelasco.notesapi.domain.notes.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "links")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private String id;

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "note_id", nullable = false)
    private NoteEntity note;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    public LinkEntity(String id, String url, NoteEntity note) {
        this.id = id;
        this.url = url;
        this.note = note;
    }
}