package com.example.backend.user_quests;


import com.example.backend.quest.Quest;
import com.example.backend.user.App_User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
@Table
public class UserQuests {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference(value="user-movement-2")
    private App_User user;

    @ManyToOne
    @JoinColumn(name = "quest_id", referencedColumnName = "id")
    @JsonBackReference(value="quest-movement")
    private Quest quest;

    private boolean completed;
    private String comment;

    public UserQuests() {
    }

    public UserQuests(App_User user, Quest quest, boolean completed, String comment) {
        this.user = user;
        this.quest = quest;
        this.completed = completed;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public App_User getUser() {
        return user;
    }

    public void setUser(App_User user) {
        this.user = user;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public String toString() {
        return "UserQuests{" +
                "id=" + id +
                ", user=" + user +
                ", quest=" + quest +
                ", completed=" + completed +
                ", comment='" + comment + '\'' +
                '}';
    }
}
