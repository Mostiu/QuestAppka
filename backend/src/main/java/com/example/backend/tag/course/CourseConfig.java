package com.example.backend.tag.course;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CourseConfig {

    @Bean
    CommandLineRunner commandLineRunnerCourse(CourseRepository repository){
        return args -> {
            Course course1 = new Course("Java", "Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible.", Difficulty.HARD);
            Course course2 = new Course("Python", "Python is an interpreted, high-level and general-purpose programming language.", Difficulty.EASY);
            repository.saveAll(List.of(course1, course2));
        };
    }
}
