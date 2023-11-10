package com.example.backend.cityChallenge;

import jakarta.persistence.*;

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

    @Override
    public String toString()
    {
        return "CityChallenge{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", created_by='" + created_by + '\'' +
                '}';

    }

}
