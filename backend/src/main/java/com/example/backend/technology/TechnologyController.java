package com.example.backend.technology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController
{
    private final TechnologyService technologyService;

    @Autowired
    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @GetMapping
    public List<Technology>getTechnologies() {
        return technologyService.getTechnologies();
    }

    @PostMapping
    public void registerNewTechnology(@RequestBody Technology technology) {
        technologyService.addNewTechnology(technology);
    }

    @DeleteMapping(path = "{technologyId}")
    public void deleteTechnology(@PathVariable("technologyId") Long technologyId) {
        technologyService.deleteTechnology(technologyId);
    }

    @PutMapping(path = "{technologyId}")
    public void updateTechnology(@PathVariable("technologyId") Long technologyId,
                                 @RequestParam(required = false) String name
                                 ) {
        technologyService.updateTechnology(technologyId, name);
    }


}
