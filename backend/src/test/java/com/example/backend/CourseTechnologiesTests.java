package com.example.backend;

import com.example.backend.course.Course;
import com.example.backend.technology.Technology;
import com.example.backend.course_technologies.CourseTechnologies;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseTechnologiesTests {

    @Test
    void testCourseTechnologies() {

        Course course = new Course();
        Technology technology = new Technology();


        CourseTechnologies courseTechnologies = new CourseTechnologies(course, technology);


        assertNotNull(courseTechnologies);
        assertEquals(course, courseTechnologies.getCourse());
        assertEquals(technology, courseTechnologies.getTechnology());
    }
}
