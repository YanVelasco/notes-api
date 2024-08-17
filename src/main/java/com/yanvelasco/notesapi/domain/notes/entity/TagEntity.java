package com.yanvelasco.notesapi.domain.notes.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yanvelasco.notesapi.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tags")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private String id;

    @Column(name = "tag_name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity user;

    public TagEntity(String name, UserEntity user) {
        this.name = name;
        this.user = user;
    }
}