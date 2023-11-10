package com.example.backend.technology;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyService
{
    private final TechnologyRepository technologyRepository;

    @Autowired
    public TechnologyService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
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


}
