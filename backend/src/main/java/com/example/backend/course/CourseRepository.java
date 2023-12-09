package com.example.backend.course;

import com.example.backend.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {


    @Query("SELECT DISTINCT tag.id, tag.name FROM Course c JOIN CourseTechnologies ct ON c.id = ct.course.id JOIN Technology t ON ct.technology.id = t.id JOIN TechnologyTags tt ON t.id = tt.technology.id JOIN Tag tag ON tt.tag.id = tag.id WHERE c.id = ?1")
    Optional<List<Tag>> getTagsFromCourse(Long id);

}
