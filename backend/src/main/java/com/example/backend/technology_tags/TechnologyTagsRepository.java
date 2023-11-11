package com.example.backend.technology_tags;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyTagsRepository extends JpaRepository<TechnologyTags, Long> {
}
