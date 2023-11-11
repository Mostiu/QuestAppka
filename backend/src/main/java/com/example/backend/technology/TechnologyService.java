package com.example.backend.technology;

import com.example.backend.tag.Tag;
import com.example.backend.tag.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyService
{
    private final TechnologyRepository technologyRepository;
    private TagRepository tagRepository;

    @Autowired
    public TechnologyService(TechnologyRepository technologyRepository, TagRepository tagRepository) {
        this.technologyRepository = technologyRepository;
        this.tagRepository = tagRepository;
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

    public void addTagToTechnology(Long technologyId, Long tagId) {
        Technology technology = this.technologyRepository.findById(technologyId).orElseThrow(() -> new IllegalStateException("Technology with id " + technologyId + " does not exists"));
        Tag tag = this.tagRepository.findById(tagId).orElseThrow(() -> new IllegalStateException("Tag with id " + tagId + " does not exists"));
        technology.addTag(tag);

        this.technologyRepository.save(technology);
    }

    public void removeTagFromTechnology(Long technologyId, Long tagId) {
        Technology technology = this.technologyRepository.findById(technologyId).orElseThrow(() -> new IllegalStateException("Technology with id " + technologyId + " does not exists"));
        Tag tag = this.tagRepository.findById(tagId).orElseThrow(() -> new IllegalStateException("Tag with id " + tagId + " does not exists"));
        technology.removeTag(tag);

        this.technologyRepository.save(technology);
    }

    @Transactional
    public void updateTechnology(Long technologyId, String name) {
        Technology technology = this.technologyRepository.findById(technologyId).orElseThrow(() -> new IllegalStateException("Technology with id " + technologyId + " does not exists"));
        if (name != null && name.length() > 0 && !technology.getName().equals(name)) {
            technology.setName(name);
        }
    }


}
