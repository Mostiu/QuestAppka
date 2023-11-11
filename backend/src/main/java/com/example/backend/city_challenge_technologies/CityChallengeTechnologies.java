package com.example.backend.city_challenge_technologies;


import com.example.backend.cityChallenge.CityChallenge;
import com.example.backend.technology.Technology;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table
public class CityChallengeTechnologies {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_challenge_id", referencedColumnName = "id")
    @JsonBackReference(value="cityChallenge-movement-2")
    private CityChallenge cityChallenge;

    @ManyToOne
    @JoinColumn(name = "technology_id", referencedColumnName = "id")
    @JsonBackReference(value="technology-movement")
    private Technology technology;

    public CityChallengeTechnologies() {
    }


    public CityChallengeTechnologies(CityChallenge cityChallenge, Technology technology) {
        this.cityChallenge = cityChallenge;
        this.technology = technology;
    }

    public Long getId() {
        return id;
    }

    public CityChallenge getCityChallenge() {
        return cityChallenge;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCityChallenge(CityChallenge cityChallenge) {
        this.cityChallenge = cityChallenge;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    @Override
    public String toString() {
        return "CityChallengeTechnologies{" +
                "id=" + id +
                ", cityChallenge=" + cityChallenge +
                ", technology=" + technology +
                '}';
    }
}
