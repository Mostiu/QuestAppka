package com.example.backend;

import com.example.backend.course.Course;
import com.example.backend.user.App_User;
import com.example.backend.user_courses.UserCourses;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserCoursesTests {

    @Mock
    private App_User user;

    @Mock
    private Course course;

    @InjectMocks
    private UserCourses userCourses;

    @Test
    void testGettersAndSetters() {
        // Set up
        userCourses.setId(1L);
        userCourses.setUser(user);
        userCourses.setCourse(course);
        userCourses.setComment("Great course!");
        userCourses.setCompleted(true);

        // Verify
        assertEquals(1L, userCourses.getId());
        assertEquals(user, userCourses.getUser());
        assertEquals(course, userCourses.getCourse());
        assertEquals("Great course!", userCourses.getComment());
        assertEquals(true, userCourses.isCompleted());
    }

    @Test
    void testToString() {
        // Set up
        userCourses.setId(1L);
        userCourses.setUser(user);
        userCourses.setCourse(course);
        userCourses.setComment("Great course!");
        userCourses.setCompleted(true);

        // Execute
        String result = userCourses.toString();

        // Verify
        assertEquals("UserCourses{id=1, user=" + user + ", course=" + course + ", comment='Great course!', completed=true}", result);
    }
}
