package com.example.backend.technology;

import jakarta.persistence.*;

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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Technology{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }



}
