package com.example.backend.course_technologies;

import com.example.backend.course.Course;
import com.example.backend.technology.Technology;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table
public class CourseTechnologies {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @JsonBackReference
    private Course course;

    @ManyToOne
    @JoinColumn(name = "technology_id", referencedColumnName = "id")
    @JsonBackReference
    private Technology technology;

    public CourseTechnologies() {
    }

    public CourseTechnologies(Course course, Technology technology) {
        this.course = course;
        this.technology = technology;
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    @Override
    public String toString() {
        return "CourseTechnologies{" +
                "id=" + id +
                ", course=" + course +
                ", technology=" + technology +
                '}';
    }
}
