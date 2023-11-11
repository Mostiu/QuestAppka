package com.example.backend.technology;

import com.example.backend.course_technologies.CourseTechnologies;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Technology {

    @Id
    @SequenceGenerator(
            name = "technology_sequence",
            sequenceName = "technology_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "technology_sequence"
    )
    private Long id;
    private String name;

    @OneToMany(mappedBy = "technology", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "technology-movement-2")
    private Set<CourseTechnologies> courseTechnologies = new HashSet<>();

    public Technology() {
    }

    public Technology(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CourseTechnologies> getCourseTechnologies() {
        return courseTechnologies;
    }

    public void setCourseTechnologies(Set<CourseTechnologies> courseTechnologies) {
        this.courseTechnologies = courseTechnologies;
    }

    @Override
    public String toString() {
        return "Technology{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseTechnologies=" + courseTechnologies +
                '}';
        }
}
