package com.example.backend.course;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public void addNewCourse(Course course) {
        courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) {
        boolean exists = courseRepository.existsById(courseId);
        if(!exists){
            throw new IllegalStateException("course with id " + courseId + " does not exists");
        }
        courseRepository.deleteById(courseId);
    }

    @Transactional
    public void updateCourse(Long courseId, String title, String description, String difficulty) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalStateException(
                "course with id " + courseId + " does not exists"
        ));

        if(title != null && title.length() > 0 && !course.getTitle().equals(title)){
            course.setTitle(title);
        }

        if(description != null && description.length() > 0 && !course.getDescription().equals(description)){
            course.setDescription(description);
        }

        if(difficulty != null && difficulty.length() > 0 && !course.getDifficulty().equals(difficulty)){
            course.setDifficulty(difficulty);
        }
    }

}
