package com.example.backend.course;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses() {
        return courseService.getCourses();
    }

    @PostMapping
    public void registerNewCourse(@RequestBody Course course) {
        courseService.addNewCourse(course);
    }

    @DeleteMapping(path="{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
    }

    @PutMapping(path="{courseId}")
    public void updateCourse(
            @PathVariable("courseId") Long courseId,
            @RequestParam(required=false) String title,
            @RequestParam(required=false) String description,
            @RequestParam(required=false) String difficulty) {
        courseService.updateCourse(courseId, title, description, difficulty);
        System.out.println("Course updated");
    }
}
