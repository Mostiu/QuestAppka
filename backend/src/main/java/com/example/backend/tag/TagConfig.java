package com.example.backend.tag;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TagConfig {

    @Bean
    CommandLineRunner commandLineRunnerTag(TagRepository repository){
        return args -> {
//            Tag tag1 = new Tag("T1");
//            Tag tag2 = new Tag("T2");
//            repository.saveAll(List.of(tag1, tag2));
        };
    }
}
