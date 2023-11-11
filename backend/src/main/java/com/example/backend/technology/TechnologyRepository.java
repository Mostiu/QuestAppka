package com.example.backend.technology;

import com.example.backend.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechnologyRepository extends JpaRepository<Technology, Long>
{
    List<Technology> findByNameContaining(String name);
}
