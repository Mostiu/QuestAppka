package com.example.backend.user_city_challenges;


import com.example.backend.cityChallenge.CityChallenge;
import com.example.backend.user.App_User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table
public class UserCityChallenges {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference(value="user-movement-3")
    private App_User user;

    @ManyToOne
    @JoinColumn(name = "city_challenge_id", referencedColumnName = "id")
    @JsonBackReference(value="cityChallenge-movement")
    private CityChallenge cityChallenge;

    private boolean completed;


    public UserCityChallenges() {
    }

    public UserCityChallenges(App_User user, CityChallenge cityChallenge, boolean completed) {
        this.user = user;
        this.cityChallenge = cityChallenge;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public App_User getUser() {
        return user;
    }

    public CityChallenge getCityChallenge() {
        return cityChallenge;
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

    public void setCityChallenge(CityChallenge cityChallenge) {
        this.cityChallenge = cityChallenge;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "UserCityChallenges{" +
                "id=" + id +
                ", user=" + user +
                ", cityChallenge=" + cityChallenge +
                ", completed=" + completed +
                '}';
    }
}
