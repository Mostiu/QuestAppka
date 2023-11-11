package com.example.backend.technology_tags;

import com.example.backend.tag.Tag;
import com.example.backend.technology.Technology;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table
public class TechnologyTags {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "technology_id", referencedColumnName = "id")
    @JsonBackReference(value = "technology-movement-3")
    private Technology technology;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    @JsonBackReference(value = "tag-movement")
    private Tag tag;


    public TechnologyTags() {
    }

    public TechnologyTags(Technology technology, Tag tag) {
        this.technology = technology;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public Technology getTechnology() {
        return technology;
    }

    public Tag getTag() {
        return tag;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "TechnologyTags{" +
                "id=" + id +
                ", technology=" + technology +
                ", tag=" + tag +
                '}';
    }
}
