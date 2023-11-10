package com.example.backend.cityChallenge;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityChallengeService {

    private final CityChallengeRepository app_cityChallengeRepository;

    @Autowired
    public CityChallengeService(CityChallengeRepository app_cityChallengeRepository) {
        this.app_cityChallengeRepository = app_cityChallengeRepository;
    }

    public List<CityChallenge> getCityChallenges() {
        return app_cityChallengeRepository.findAll();
    }

    public void addNewCityChallenge(CityChallenge cityChallenge) {
        Optional<CityChallenge> cityChallengeOptional = app_cityChallengeRepository.findCityChallengeByTitle(cityChallenge.getTitle());

        if(cityChallengeOptional.isPresent()){
            throw new IllegalStateException("name taken");
        }
        app_cityChallengeRepository.save(cityChallenge);
    }

    public void deleteCityChallenge(Long cityChallengeId) {
        boolean exists = app_cityChallengeRepository.existsById(cityChallengeId);
        if(!exists){
            throw new IllegalStateException("cityChallenge with id " + cityChallengeId + " does not exists");
        }
        app_cityChallengeRepository.deleteById(cityChallengeId);
    }

    @Transactional
    public void updateCityChallenge(Long cityChallengeId, String title, String description, String created_by) {
        CityChallenge cityChallenge = app_cityChallengeRepository.findById(cityChallengeId).orElseThrow(() -> new IllegalStateException(
                "cityChallenge with id " + cityChallengeId + " does not exists"
        ));

        if(title != null && title.length() > 0 && !cityChallenge.getTitle().equals(title)){
            Optional<CityChallenge> cityChallengeOptional = app_cityChallengeRepository.findCityChallengeByTitle(title);

            if(cityChallengeOptional.isPresent()){
                throw new IllegalStateException("title taken");
            }
            cityChallenge.setTitle(title);
        }

        if(description != null && description.length() > 0 && !cityChallenge.getDescription().equals(description)){
            cityChallenge.setDescription(description);
        }

        if(created_by != null && created_by.length() > 0 && !cityChallenge.getCreated_by().equals(created_by)){
            cityChallenge.setCreated_by(created_by);
        }
    }


}