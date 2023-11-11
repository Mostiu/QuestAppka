package com.example.backend.cityChallenge;

import com.example.backend.city_challenge_technologies.CityChallengeTechnologies;
import com.example.backend.user_city_challenges.UserCityChallenges;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class CityChallenge
{
@Id
@SequenceGenerator(
        name = "city_challenge_sequence",
        sequenceName = "city_challenge_sequence",
        allocationSize = 1
)


@GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "city_challenge_sequence"
)
private Long id;
private String title;
private String description;
private String created_by;

@OneToMany(mappedBy = "cityChallenge",fetch = FetchType.EAGER)
@JsonManagedReference(value="cityChallenge-movement")
private Set<UserCityChallenges> userCityChallenges = new HashSet<>();

@OneToMany(mappedBy = "cityChallenge",fetch = FetchType.EAGER)
@JsonManagedReference(value="cityChallenge-movement-2")
private Set<CityChallengeTechnologies> cityChallengeTechnologies = new HashSet<>();


public CityChallenge() {
}
public CityChallenge(String title, String description, String created_by) {
    this.title = title;
    this.description = description;
    this.created_by = created_by;
}

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getCreated_by()
    {
        return created_by;
    }

    public void setCreated_by(String created_by)
    {
        this.created_by = created_by;
    }

    public Set<UserCityChallenges> getUserCityChallenges()
    {
        return userCityChallenges;
    }

    public void setUserCityChallenges(Set<UserCityChallenges> userCityChallenges)
    {
        this.userCityChallenges = userCityChallenges;
    }

    public void addUserCityChallenges(UserCityChallenges userCityChallenges)
    {
        this.userCityChallenges.add(userCityChallenges);
    }

    public Set<CityChallengeTechnologies> getCityChallengeTechnologies()
    {
        return cityChallengeTechnologies;
    }

    public void setCityChallengeTechnologies(Set<CityChallengeTechnologies> cityChallengeTechnologies)
    {
        this.cityChallengeTechnologies = cityChallengeTechnologies;
    }

    public void addCityChallengeTechnologies(CityChallengeTechnologies cityChallengeTechnologies)
    {
        this.cityChallengeTechnologies.add(cityChallengeTechnologies);
    }

    @Override
    public String toString()
    {
        return "CityChallenge{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", created_by='" + created_by + '\'' +
                ", userCityChallenges='" + userCityChallenges + '\'' +
                ", cityChallengeTechnologies='" + cityChallengeTechnologies + '\'' +
                '}';

    }

}
