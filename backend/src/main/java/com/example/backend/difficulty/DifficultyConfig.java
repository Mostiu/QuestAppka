package com.example.backend.difficulty;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DifficultyConfig {

    @Bean
    CommandLineRunner commandLineRunnerDifficulty(DifficultyRepository repository){
        return args -> {
            Difficulty difficulty1 = new Difficulty("Beginner");
            Difficulty difficulty2 = new Difficulty("Intermediate");
            Difficulty difficulty3 = new Difficulty("Advanced");
            repository.saveAll(List.of(difficulty1, difficulty2, difficulty3));
        };
    }
}
