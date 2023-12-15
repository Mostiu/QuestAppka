package com.example.backend.course;

import com.example.backend.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    @Query("SELECT tag " +
            "FROM Technology t " +
            "JOIN TechnologyTags tt ON t.id = tt.technology.id " +
            "JOIN Tag tag ON tt.tag.id = tag.id " +
            "JOIN CourseTechnologies ct ON t.id = ct.technology.id " +
            "WHERE ct.course.id = :courseId")
    List<Tag> getTagsByCourseId(@Param("courseId") Long courseId);

}
