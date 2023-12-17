package com.example.backend.course;

import com.example.backend.technology.Technology;

import java.util.List;

public class CourseAddBody {

    private String title;
    private String description;
    private Difficulty difficulty;
    private List<Long> technologies;
    private List<String> quests;


    public CourseAddBody() {
    }

    public CourseAddBody(String title, String description, Difficulty difficulty, List<Long> technologies, List<String> quests) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.technologies = technologies;
        this.quests = quests;
    }

    public CourseAddBody(String title, String description, List<Long> technologies, List<String> quests) {
        this.title = title;
        this.description = description;
        this.technologies = technologies;
        this.quests = quests;
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

    public List<Long> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Long> technologies) {
        this.technologies = technologies;
    }

    public List<String> getQuests() {
        return quests;
    }

    public void setQuests(List<String> quests) {
        this.quests = quests;
    }
}
