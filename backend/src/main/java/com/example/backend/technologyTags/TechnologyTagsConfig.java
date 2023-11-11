package com.example.backend.technologyTags;

import com.example.backend.tag.Tag;
import com.example.backend.tag.TagRepository;


import com.example.backend.technology.Technology;
import com.example.backend.technology.TechnologyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class TechnologyTagsConfig
{
    @Bean
    CommandLineRunner commandLineRunnerTag(TechnologyRepository technologyRepository, TagRepository tagRepository){
        return args -> {
            Technology technology1 = new Technology("T1");
               Technology technology2 = new Technology("T2");

                technologyRepository.saveAll(List.of(technology1, technology2));
        };
    }
}
