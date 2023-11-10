package com.example.backend.difficulty;


import jakarta.persistence.*;

@Entity
@Table
public class Difficulty {

    @Id
    @SequenceGenerator(
            name = "difficulty_sequence",
            sequenceName = "difficulty_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "difficulty_sequence"
    )
    private Long id;
    private String name;

    public Difficulty() {
    }

    public Difficulty(String name) {
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

    @Override
    public String toString() {
        return "Difficulty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
