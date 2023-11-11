package com.example.backend.technology;


import com.example.backend.tag.Tag;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TechnologyConfig
{

    @Bean
    CommandLineRunner commandLineRunnerTechnology(TechnologyRepository repository){
        return args -> {
            Technology technology1 = new Technology("T1");
            Technology technology2 = new Technology("T2");

            technology1.addTag(new Tag("K1"));


            repository.saveAll(List.of(technology1, technology2));
        };
    }
}
