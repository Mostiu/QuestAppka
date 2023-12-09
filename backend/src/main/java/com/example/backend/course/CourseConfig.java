package com.example.backend.course;


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
            Course course3 = new Course("C++", "C++ is a general-purpose programming language created by Bjarne Stroustrup as an extension of the C programming language, or 'C with Classes'.", Difficulty.MEDIUM);
            Course course4 = new Course("C#", "C# is a general-purpose, multi-paradigm programming language encompassing static typing, strong typing, lexically scoped, imperative, declarative, functional, generic, object-oriented, and component-oriented programming disciplines.", Difficulty.MEDIUM);
            Course course5 = new Course("JavaScript", "JavaScript, often abbreviated as JS, is a programming language that conforms to the ECMAScript specification.", Difficulty.EASY);
            Course course6 = new Course("PHP", "PHP is a general-purpose scripting language especially suited to web development.", Difficulty.EASY);
            Course course7 = new Course("SQL", "SQL is a domain-specific language used in programming and designed for managing data held in a relational database management system, or for stream processing in a relational data stream management system.", Difficulty.MEDIUM);
            Course course8 = new Course("HTML", "Hypertext Markup Language is the standard markup language for documents designed to be displayed in a web browser.", Difficulty.EASY);
            Course course9 = new Course("CSS", "Cascading Style Sheets is a style sheet language used for describing the presentation of a document written in a markup language such as HTML.", Difficulty.EASY);
            Course course10 = new Course("TypeScript", "TypeScript is a programming language developed and maintained by Microsoft.", Difficulty.MEDIUM);

            repository.saveAll(List.of(course1, course2, course3, course4, course5, course6, course7, course8, course9, course10));
        };
    }
}
