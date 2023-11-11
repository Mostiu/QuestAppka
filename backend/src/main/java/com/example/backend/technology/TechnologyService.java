package com.example.backend.technology;

import com.example.backend.tag.Tag;
import com.example.backend.tag.TagRepository;
import com.example.backend.technology_tags.TechnologyTags;
import com.example.backend.technology_tags.TechnologyTagsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyService
{
    private final TechnologyRepository technologyRepository;

    private final TagRepository tagRepository;

    private final TechnologyTagsRepository technologyTagRepository;

    @Autowired
    public TechnologyService(TechnologyRepository technologyRepository, TagRepository tagRepository, TechnologyTagsRepository technologyTagRepository) {
        this.technologyRepository = technologyRepository;
        this.tagRepository = tagRepository;
        this.technologyTagRepository = technologyTagRepository;
    }

    public List<Technology> getTechnologies() {
        return this.technologyRepository.findAll();
    }

    public void addNewTechnology(Technology technology) {
        this.technologyRepository.save(technology);
    }

    public void deleteTechnology(Long technologyId) {
        boolean exists = this.technologyRepository.existsById(technologyId);
        if (!exists) {
            throw new IllegalStateException("Technology with id " + technologyId + " does not exists");
        }
        this.technologyRepository.deleteById(technologyId);
    }

    @Transactional
    public void updateTechnology(Long technologyId, String name) {
        Technology technology = this.technologyRepository.findById(technologyId).orElseThrow(() -> new IllegalStateException("Technology with id " + technologyId + " does not exists"));
        if (name != null && name.length() > 0 && !technology.getName().equals(name)) {
            technology.setName(name);
        }
    }


    @Transactional
    public void addTagToTechnology(Long technologyId, Long tagId) {
        Technology technology = technologyRepository.findById(technologyId).orElseThrow(() -> new IllegalStateException("Technology with id " + technologyId + " does not exists"));
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new IllegalStateException("Tag with id " + tagId + " does not exists"));

        TechnologyTags technologyTag = new TechnologyTags(technology, tag);

        technology.addTechnologyTags(technologyTag);
        tag.addTechnologyTags(technologyTag);

        technologyRepository.save(technology);
        tagRepository.save(tag);
        technologyTagRepository.save(technologyTag);

    }
}
