package com.example.backend;

import com.example.backend.course.Course;
import com.example.backend.course.Difficulty;
import com.example.backend.course_technologies.CourseTechnologies;
import com.example.backend.quest.Quest;
import com.example.backend.user_courses.UserCourses;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class CourseTests {

    @Test
    void testCourse() {
        // Arrange
        String title = "Test Course";
        String description = "Test Description";
        Difficulty difficulty = Difficulty.EASY;

        Course course = new Course(title, description, difficulty);

        UserCourses userCourses = new UserCourses(/* inicjalizacja obiektu UserCourses */);
        CourseTechnologies courseTechnologies = new CourseTechnologies(/* inicjalizacja obiektu CourseTechnologies */);
        Quest quest = new Quest(/* inicjalizacja obiektu Quest */);

        // Act
        course.addUserCourses(userCourses);
        course.addCourseTechnologies(courseTechnologies);
        course.addQuests(quest);

        // Assert
        assertNotNull(course);
        assertEquals(title, course.getTitle());
        assertEquals(description, course.getDescription());
        assertEquals(difficulty, course.getDifficulty());

        Set<UserCourses> userCoursesSet = new HashSet<>();
        userCoursesSet.add(userCourses);
        assertEquals(userCoursesSet, course.getUserCourses());

        Set<CourseTechnologies> courseTechnologiesSet = new HashSet<>();
        courseTechnologiesSet.add(courseTechnologies);
        assertEquals(courseTechnologiesSet, course.getCourseTechnologies());

        Set<Quest> questsSet = new HashSet<>();
        questsSet.add(quest);
        assertEquals(questsSet, course.getQuests());
    }
}
