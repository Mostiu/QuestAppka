package com.example.backend.course;

import com.example.backend.course_technologies.CourseTechnologies;
import com.example.backend.quest.Quest;
import com.example.backend.user_courses.UserCourses;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


enum Difficulty {
    EASY,
    MEDIUM,
    HARD
}

@Entity
@Table
public class Course {


    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @OneToMany(mappedBy = "course",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="course-movement")
    private Set<UserCourses> userCourses = new HashSet<>();

    @OneToMany(mappedBy = "course",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="course-movement-2")
    private Set<CourseTechnologies> courseTechnologies = new HashSet<>();

    @OneToMany(mappedBy = "course",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="course-movement-3")
    private Set<Quest> quests = new HashSet<>();

    public Course() {
    }

    public Course(String title, String description, Difficulty difficulty) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Set<UserCourses> getUserCourses() {
        return userCourses;
    }

    public Set<Quest> getQuestsFromCourse() {
        return quests;
    }

    public void setUserCourses(Set<UserCourses> userCourses) {
        this.userCourses = userCourses;
    }

    public void addUserCourses(UserCourses userCourses) {
        this.userCourses.add(userCourses);
    }

    public Set<CourseTechnologies> getCourseTechnologies() {
        return courseTechnologies;
    }

    public void setCourseTechnologies(Set<CourseTechnologies> courseTechnologies) {
        this.courseTechnologies = courseTechnologies;
    }

    public void addCourseTechnologies(CourseTechnologies courseTechnologies) {
        this.courseTechnologies.add(courseTechnologies);
    }

    public Set<Quest> getQuests() {
        return quests;
    }

    public void setQuests(Set<Quest> quests) {
        this.quests = quests;
    }

    public void addQuests(Quest quest) {
        this.quests.add(quest);
    }



    @Override

    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", userCourses=" + userCourses + '\'' +
                ", courseTechnologies=" + courseTechnologies +
                '}';
    }
}
