package com.example.backend.user_courses;

import com.example.backend.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCoursesRepository extends JpaRepository<UserCourses,Long> {


    @Query("SELECT c FROM Course c " +
            "JOIN UserCourses uc ON c.id = uc.course.id " +
            "JOIN App_User u ON u.id = uc.user.id " +
            "WHERE u.email = :userEmail")
    List<Course> getCoursesByUserEmail(@Param("userEmail") String userEmail);



}
