package com.example.backend.user;

import com.example.backend.course.Course;
import com.example.backend.course.CourseRepository;
import com.example.backend.user_courses.UserCourses;
import com.example.backend.user_courses.UserCoursesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository app_userRepository;

    private final CourseRepository courseRepository;

    private final UserCoursesRepository userCoursesRepository;

    @Autowired
    public UserService(UserRepository app_userRepository, CourseRepository courseRepository, UserCoursesRepository userCoursesRepository) {
        this.app_userRepository = app_userRepository;
        this.courseRepository = courseRepository;
        this.userCoursesRepository = userCoursesRepository;
    }

    public List<App_User> getUsers() {
        return app_userRepository.findAll();
    }

    public void addNewUser(App_User user) {
        Optional<App_User> userOptional = app_userRepository.findUserByEmail(user.getEmail());

        if(userOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        app_userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = app_userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("user with id " + userId + " does not exists");
        }
        app_userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String name, String email) {
        App_User user = app_userRepository.findById(userId).orElseThrow(() -> new IllegalStateException(
                "user with id " + userId + " does not exists"
        ));

        if(name != null && name.length() > 0 && !user.getName().equals(name)){
            user.setName(name);
        }

        if(email != null && email.length() > 0 && !user.getEmail().equals(email)){
            Optional<App_User> userOptional = app_userRepository.findUserByEmail(email);
            if(userOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            user.setEmail(email);
        }
    }

    @Transactional
    public void enrollUser(Long userId, Long courseId) {
        App_User user = app_userRepository.findById(userId).orElseThrow(() -> new IllegalStateException(
                "user with id " + userId + " does not exists"
        ));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalStateException(
                "course with id " + courseId + " does not exists"
        ));

        UserCourses userCourses = new UserCourses();
        userCourses.setUser(user);
        userCourses.setCourse(course);
        userCourses.setCompleted(false);
        userCourses.setComment("");

        user.addUserCourses(userCourses);
        course.addUserCourses(userCourses);

        userCoursesRepository.save(userCourses);
        app_userRepository.save(user);
        courseRepository.save(course);

    }
}
