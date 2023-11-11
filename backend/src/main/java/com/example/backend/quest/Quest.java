package com.example.backend.quest;

import com.example.backend.course.Course;
import com.example.backend.user_quests.UserQuests;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Quest {

    @Id
    @SequenceGenerator(
            name = "quest_sequence",
            sequenceName = "quest_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "quest_sequence"
    )
    private Long id;
    private String name;
    private String description;
    private boolean is_completed;
    private String comment;


    @OneToMany(mappedBy = "quest",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="quest-movement")
    private Set<UserQuests> userQuests = new HashSet<>();


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @JsonBackReference(value="course-movement-3")
    private Course course = null;

    public Quest() {
    }

    public Quest(String name, String description, boolean is_completed, String comment) {
        this.name = name;
        this.description = description;
        this.is_completed = is_completed;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIs_completed() {
        return is_completed;
    }

    public void setIs_completed(boolean is_completed) {
        this.is_completed = is_completed;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Quest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", is_completed=" + is_completed +
                ", comment='" + comment + '\'' +
                ", userQuests=" + userQuests +
                ", course=" + course +
                '}';
    }

    public void addUserQuests(UserQuests userQuests) {
        this.userQuests.add(userQuests);
    }

    public Set<UserQuests> getUserQuests()
    {
        return userQuests;
    }
}
