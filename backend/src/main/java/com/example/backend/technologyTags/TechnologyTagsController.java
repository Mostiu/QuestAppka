package com.example.backend.technologyTags;


import com.example.backend.tag.Tag;
import com.example.backend.tag.TagRepository;
import com.example.backend.technology.Technology;
import com.example.backend.technology.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/technologies/tags")
public class TechnologyTagsController
{
    private TechnologyRepository technologyRepository;
    private TagRepository tagRepository;

    public TechnologyTagsController(TechnologyRepository technologyRepository, TagRepository tagRepository)
    {
        this.technologyRepository = technologyRepository;
        this.tagRepository = tagRepository;
    }

    @PostMapping
    public Technology saveTechnologyWithTags(@RequestBody Technology technology)
    {

        return technologyRepository.save(technology);
    }

    @GetMapping
    public List<Technology> getTechnologiesWithTags()
    {
        return technologyRepository.findAll();
    }

    @GetMapping("/{technologyId}")
    public Technology findTechnologyById(@PathVariable Long technologyId)
    {
        return technologyRepository.findById(technologyId).orElseThrow(() -> new IllegalStateException("Technology with id " + technologyId + " does not exists"));
    }

    @GetMapping("/find/{name}")
    public List<Technology> findTechnologyContainingByName(@PathVariable String name)
    {
        return technologyRepository.findByNameContaining(name);
    }

    @GetMapping("/search/{name}")
    public List<Tag> findTagContainingByName(@PathVariable String name)
    {
        return tagRepository.findByNameContaining(name);
    }
}
