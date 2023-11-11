package com.example.backend.course;


import com.example.backend.course_technologies.CourseTechnologies;
import com.example.backend.course_technologies.CourseTechnologiesRepository;
import com.example.backend.technology.Technology;
import com.example.backend.technology.TechnologyRepository;
import com.example.backend.user.App_User;
import com.example.backend.user_courses.UserCourses;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TechnologyRepository technologyRepository;

    private final CourseTechnologiesRepository courseTechnologiesRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, TechnologyRepository technologyRepository, CourseTechnologiesRepository courseTechnologiesRepository) {
        this.courseRepository = courseRepository;
        this.technologyRepository = technologyRepository;
        this.courseTechnologiesRepository = courseTechnologiesRepository;
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

        Course c = courseRepository.findById(courseId).orElseThrow(() -> new IllegalStateException(
                "course with id " + courseId + " does not exists"
        ));

        //disassociate CourseTechnologies from Technology
        Set<CourseTechnologies> courseTechnologies = c.getCourseTechnologies();
        if(courseTechnologies != null && !((Set<?>) courseTechnologies).isEmpty()){
            for(CourseTechnologies courseTechnology : courseTechnologies){
                Technology technology = courseTechnology.getTechnology();
                if(technology != null){
                    technology.getCourseTechnologies().remove(courseTechnology);
                }
                courseTechnology.setTechnology(null);
            }
            courseTechnologies.clear();
        }

        //disassociate UserCourses from App_User
        Set<UserCourses> userCourses = c.getUserCourses();
        if(userCourses != null && !((Set<?>) userCourses).isEmpty()){
            for(UserCourses userCourse : userCourses){
                App_User user = userCourse.getUser();
                if(user != null){
                    user.getUserCourses().remove(userCourse);
                }
                userCourse.setUser(null);
            }
            userCourses.clear();
        }


        courseRepository.deleteById(courseId);
    }

    @Transactional
    public void updateCourse(Long courseId, String title, String description, Difficulty difficulty) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalStateException(
                "course with id " + courseId + " does not exists"
        ));

        if(title != null && title.length() > 0 && !course.getTitle().equals(title)){
            course.setTitle(title);
        }

        if(description != null && description.length() > 0 && !course.getDescription().equals(description)){
            course.setDescription(description);
        }

        if(difficulty != null  && !course.getDifficulty().equals(difficulty)){
            course.setDifficulty(difficulty);
        }
    }

    @Transactional
    public void addTechnologyToCourse(Long courseId, Long technologyId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalStateException(
                "course with id " + courseId + " does not exists"
        ));

        Technology technology = technologyRepository.findById(technologyId).orElseThrow(() -> new IllegalStateException(
                "technology with id " + technologyId + " does not exists"
        ));

        CourseTechnologies courseTechnologies = new CourseTechnologies(course, technology);

        courseRepository.save(course);
        technologyRepository.save(technology);
        courseTechnologiesRepository.save(courseTechnologies);
    }
}
