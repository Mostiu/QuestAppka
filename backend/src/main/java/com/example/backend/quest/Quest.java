package com.example.backend.quest;

import jakarta.persistence.*;

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

    @Override
    public String toString() {
        return "Quest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", is_completed=" + is_completed +
                ", comment='" + comment + '\'' +
                '}';
    }
}
