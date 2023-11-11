package com.example.backend.course_technologies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTechnologiesRepository extends JpaRepository<CourseTechnologies, Long> {
}
