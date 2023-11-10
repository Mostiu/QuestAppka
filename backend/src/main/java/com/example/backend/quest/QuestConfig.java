package com.example.backend.quest;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class QuestConfig {

    @Bean
    CommandLineRunner commandLineRunnerQuest(QuestRepository repository){
        return args -> {
            Quest quest1 = new Quest("Q1","Q1 description", false, "Q1 comment");
            Quest quest2 = new Quest("Q2","Q2 description", false, "Q2 comment");
            repository.saveAll(List.of(quest1, quest2));
        };
    }
}
