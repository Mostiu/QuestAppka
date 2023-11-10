package com.example.backend.user;

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
        Optional<CityChallenge> cityChallengeOptional = app_cityChallengeRepository.findCityChallengeByName(cityChallenge.getName());

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
    public void updateCityChallenge(Long cityChallengeId, String name, String description, String author) {
        CityChallenge cityChallenge = app_cityChallengeRepository.findById(cityChallengeId).orElseThrow(() -> new IllegalStateException(
                "cityChallenge with id " + cityChallengeId + " does not exists"
        ));

        if(name != null && name.length() > 0 && !cityChallenge.getName().equals(name)){
            Optional<CityChallenge> cityChallengeOptional = app_cityChallengeRepository.findCityChallengeByName(name);
            if(cityChallengeOptional.isPresent()){
                throw new IllegalStateException("name taken");
            }
            cityChallenge.setName(name);
        }

        if(description != null && description.length() > 0 && !cityChallenge.getDescription().equals(description)){
            cityChallenge.setDescription(description);
        }

        if(author != null && author.length() > 0 && !cityChallenge.getAuthor().equals(author)){
            cityChallenge.setAuthor(author);
        }
    }


}