package com.example.backend.tag;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> getTags() {
        return tagService.getTags();
    }

    @PostMapping
    public void registerNewTag(@RequestBody Tag tag) {
        tagService.addNewTag(tag);
    }

    @DeleteMapping(path="{tagId}")
    public void deleteTag(@PathVariable Long tagId) {
        tagService.deleteTag(tagId);
    }

    @PutMapping(path="{tagId}")
    public void updateTag(
            @PathVariable("tagId") Long tagId,
            @RequestParam(required=false) String name) {
        tagService.updateTag(tagId, name);
        System.out.println("Tag updated");
    }

}
