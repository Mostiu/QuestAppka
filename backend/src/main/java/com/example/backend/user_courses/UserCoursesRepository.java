package com.example.backend.user_courses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCoursesRepository extends JpaRepository<UserCourses,Long> {
}
