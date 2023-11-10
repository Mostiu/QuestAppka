package com.example.backend.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CityChallengeConfig {

    @Bean
    CommandLineRunner commandLineRunner(CityChallengeRepository repository){
        return args -> {
            CityChallenge cityChallenge1 = new CityChallenge("Snake", "Big snake is snaking","Johnny Bravo");
            CityChallenge cityChallenge2 = new CityChallenge("Tetris", "Big blocks are falling","Geralt of Rivia");
            repository.saveAll(List.of(cityChallenge1, cityChallenge2));
        };
    }
}