package com.example.backend.cityChallenge;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/cityChallenges")
public class CityChallengeController{

    private final CityChallengeService cityChallengeService;

    @Autowired
    public CityChallengeController(CityChallengeService cityChallengeService) {
        this.cityChallengeService = cityChallengeService;
    }

    @GetMapping
    public List<CityChallenge> getCityChallenges() {
        return cityChallengeService.getCityChallenges();
    }

    @PostMapping
    public void registerNewCityChallenge(@RequestBody CityChallenge cityChallenge) {
    	cityChallengeService.addNewCityChallenge(cityChallenge);
    }

    @DeleteMapping(path="{cityChallengeId}")
    public void deleteCityChallenge(@PathVariable("cityChallengeId") Long cityChallengeId) {
    	cityChallengeService.deleteCityChallenge(cityChallengeId);
    }

    @PutMapping(path="{cityChallengeId}")
    public void updateCityChallenge(
    		@PathVariable("cityChallengeId") Long cityChallengeId,
    		@RequestParam(required=false) String title,
    		@RequestParam(required=false) String description,
    		@RequestParam(required=false) String created_by) {
    	cityChallengeService.updateCityChallenge(cityChallengeId, title, description, created_by);
    }


}