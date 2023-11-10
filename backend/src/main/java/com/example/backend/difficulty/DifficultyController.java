package com.example.backend.difficulty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/difficulties")
public class DifficultyController {

    private final DifficultyService difficultyService;

    @Autowired
    public DifficultyController(DifficultyService difficultyService) {
        this.difficultyService = difficultyService;
    }

    @GetMapping
    public List<Difficulty> getDifficulties() {
        return difficultyService.getDifficulties();
    }

    @PostMapping
    public void registerNewDifficulty(@RequestBody Difficulty difficulty) {
        difficultyService.addNewDifficulty(difficulty);
    }

    @DeleteMapping(path="{difficultyId}")
    public void deleteDifficulty(@PathVariable Long difficultyId) {
        difficultyService.deleteDifficulty(difficultyId);
    }

    @PutMapping(path="{difficultyId}")
    public void updateDifficulty(
            @PathVariable("difficultyId") Long difficultyId,
            @RequestParam(required=false) String name) {
        difficultyService.updateDifficulty(difficultyId, name);
        System.out.println("Difficulty updated");
    }
}
