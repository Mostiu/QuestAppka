package com.example.backend.technology;

import com.example.backend.tag.Tag;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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




    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "technology_tags",
            joinColumns = @JoinColumn(name = "technologyId"),
            inverseJoinColumns = @JoinColumn(name = "tagId"))
    private Set<Tag> tags = new HashSet<Tag>();

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

    public Set<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Technology{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }


}
