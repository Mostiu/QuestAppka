package com.example.backend.tag;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getTags() {
        return tagRepository.findAll();
    }

    public void addNewTag(Tag tag) {
        tagRepository.save(tag);
    }

    public void deleteTag(Long tagId) {
        boolean exists = tagRepository.existsById(tagId);
        if(!exists){
            throw new IllegalStateException("tag with id " + tagId + " does not exists");
        }
        tagRepository.deleteById(tagId);
    }

    @Transactional
    public void updateTag(Long tagId, String name) {
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new IllegalStateException(
                "tag with id " + tagId + " does not exists"
        ));

        if(name != null && name.length() > 0 && !tag.getName().equals(name)){
            tag.setName(name);
        }
    }

    public void getTechnologiesByTag(Long tagId) {
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new IllegalStateException(
                "tag with id " + tagId + " does not exists"
        ));
        tag.getTechnologies();
    }
}
