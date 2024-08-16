package com.yanvelasco.notesapi.domain.links.repository;

import com.yanvelasco.notesapi.domain.links.entity.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<LinkEntity, String> {
}