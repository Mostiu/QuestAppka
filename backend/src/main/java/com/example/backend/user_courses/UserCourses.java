package com.example.backend.user_courses;

import com.example.backend.course.Course;
import com.example.backend.user.App_User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table
public class UserCourses {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference(value="user-movement")
    private App_User user;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @JsonBackReference(value="course-movement")
    private Course course;

    private String comment;
    private boolean completed;

    public UserCourses() {
    }

    public UserCourses(App_User user, Course course, String comment, boolean completed) {
        this.user = user;
        this.course = course;
        this.comment = comment;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public App_User getUser() {
        return user;
    }

    public Course getCourse() {
        return course;
    }

    public String getComment() {
        return comment;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(App_User user) {
        this.user = user;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserCourses{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", course=").append(course);
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", completed=").append(completed);
        sb.append('}');
        return sb.toString();
    }
}
