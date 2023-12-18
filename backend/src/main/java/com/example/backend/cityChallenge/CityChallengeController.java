package com.example.backend.cityChallenge;


import com.example.backend.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(path="api/cityChallenges")
public class CityChallengeController{

    private final CityChallengeService cityChallengeService;

    @Autowired
    public CityChallengeController(CityChallengeService cityChallengeService) {
        this.cityChallengeService = cityChallengeService;
    }


    @GetMapping
    public List<Object[]> getCityChallenges() {
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

    @PutMapping(path="{cityChallengeId}/add/technology/{technologyId}")
    public void addTechnologyToCityChallenge(@PathVariable Long cityChallengeId, @PathVariable Long technologyId) {
    	cityChallengeService.addTechnologyToCityChallenge(cityChallengeId, technologyId);
    }

    @GetMapping(path="{cityChallengeId}/tags")
    public List<String> getTagsFromCityChallenge(@PathVariable Long cityChallengeId) {
    	return cityChallengeService.getTagsFromCityChallenge(cityChallengeId);
    }

    @GetMapping(path="{cityChallengeId}")
    public Object[] getCityChallenge(@PathVariable Long cityChallengeId) {
    	return cityChallengeService.getCityChallenge(cityChallengeId);
    }

    @GetMapping(path="{cityChallengeId}/submitted")
    public List<Object[]> getSubmittedCityChallenge(@PathVariable Long cityChallengeId) {
    	return cityChallengeService.getSubmittedCityChallenge(cityChallengeId);
    }
}
