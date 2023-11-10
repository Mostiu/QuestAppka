package com.example.backend.difficulty;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DifficultyService {

    private final DifficultyRepository difficultyRepository;

    @Autowired
    public DifficultyService(DifficultyRepository difficultyRepository) {
        this.difficultyRepository = difficultyRepository;
    }

    public List<Difficulty> getDifficulties() {
        return difficultyRepository.findAll();
    }

    public void addNewDifficulty(Difficulty difficulty) {
        difficultyRepository.save(difficulty);
    }

    public void deleteDifficulty(Long difficultyId) {
        boolean exists = difficultyRepository.existsById(difficultyId);
        if(!exists){
            throw new IllegalStateException("difficulty with id " + difficultyId + " does not exists");
        }
        difficultyRepository.deleteById(difficultyId);
    }

    @Transactional
    public void updateDifficulty(Long difficultyId, String name) {
        Difficulty difficulty = difficultyRepository.findById(difficultyId).orElseThrow(() -> new IllegalStateException(
                "difficulty with id " + difficultyId + " does not exists"
        ));

        if(name != null && name.length() > 0 && !difficulty.getName().equals(name)){
            difficulty.setName(name);
        }
    }
}
