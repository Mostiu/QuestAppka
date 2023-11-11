package com.example.backend.tag;

import com.example.backend.technology.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByNameContaining(String name);
}
