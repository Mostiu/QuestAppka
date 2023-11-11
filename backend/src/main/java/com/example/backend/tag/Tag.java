package com.example.backend.tag;


import com.example.backend.technology_tags.TechnologyTags;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Tag {

    @Id
    @SequenceGenerator(
            name = "tag_sequence",
            sequenceName = "tag_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tag_sequence"
    )
    private Long id;
    private String name;

    @OneToMany(mappedBy = "tag", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "tag-movement")
    private Set<TechnologyTags> technologyTags = new HashSet<>();


    public Tag() {
    }
    public Tag(String name) {
        this.name = name;
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

    public Set<TechnologyTags> getTechnologyTags() {
        return technologyTags;
    }

    public void setTechnologyTags(Set<TechnologyTags> technologyTags) {
        this.technologyTags = technologyTags;
    }

    public void addTechnologyTags(TechnologyTags technologyTags) {
        this.technologyTags.add(technologyTags);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", technologyTags=" + technologyTags +
                '}';
    }
}
