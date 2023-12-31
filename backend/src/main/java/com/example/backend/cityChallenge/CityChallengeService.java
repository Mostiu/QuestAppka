package com.example.backend.cityChallenge;

import com.example.backend.city_challenge_technologies.CityChallengeTechnologies;
import com.example.backend.city_challenge_technologies.CityChallengeTechnologiesRepository;
import com.example.backend.technology.Technology;
import com.example.backend.technology.TechnologyRepository;
import com.example.backend.user.App_User;
import com.example.backend.user_city_challenges.UserCityChallenges;
import com.example.backend.user_city_challenges.UserCityChallengesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CityChallengeService {

    private final CityChallengeRepository app_cityChallengeRepository;

    private final CityChallengeTechnologiesRepository cityChallengeTechnologyRepository;

    private final TechnologyRepository technologyRepository;

    private final UserCityChallengesRepository userCityChallengesRepository;

    @Autowired
    public CityChallengeService(CityChallengeRepository app_cityChallengeRepository,
                                CityChallengeTechnologiesRepository cityChallengeTechnologyRepository,
                                TechnologyRepository technologyRepository, UserCityChallengesRepository userCityChallengesRepository) {
        this.app_cityChallengeRepository = app_cityChallengeRepository;
        this.cityChallengeTechnologyRepository = cityChallengeTechnologyRepository;
        this.technologyRepository = technologyRepository;
        this.userCityChallengesRepository = userCityChallengesRepository;
    }

    public List<Object[]> getCityChallenges() {
        return app_cityChallengeRepository.getCityChallengesInfo();
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
        if (!exists) {
            throw new IllegalStateException("CityChallenge with id " + cityChallengeId + " does not exist");
        }

        CityChallenge cityChallenge = app_cityChallengeRepository.findById(cityChallengeId)
                .orElseThrow(() -> new IllegalStateException("CityChallenge not found"));

        // Disassociate CityChallengeTechnologies from Technology
        Set<CityChallengeTechnologies> cityChallengeTechnologies = cityChallenge.getCityChallengeTechnologies();
        if (cityChallengeTechnologies != null && !((Set<?>) cityChallengeTechnologies).isEmpty()) {
            for (CityChallengeTechnologies cityChallengeTechnology : cityChallengeTechnologies) {
                Technology technology = cityChallengeTechnology.getTechnology();
                if (technology != null) {
                    technology.removeCityChallengeTechnologies(cityChallengeTechnology);
                }
            }
        }


      // Disassociate UserCityChallenge from User

        Set<UserCityChallenges> userCityChallenges = cityChallenge.getUserCityChallenges();
        if (userCityChallenges != null && !((Set<?>) userCityChallenges).isEmpty()) {
            for (UserCityChallenges userCityChallenge : userCityChallenges) {
                App_User user = userCityChallenge.getUser();
                if (user != null) {
                    user.removeUserCityChallenges(userCityChallenge);
                }
            }
        }

        // Delete the CityChallenge
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


    @Transactional
    public void addTechnologyToCityChallenge(Long cityChallengeId, Long technologyId) {
        CityChallenge cityChallenge = app_cityChallengeRepository.findById(cityChallengeId).orElseThrow(() -> new IllegalStateException(
                "cityChallenge with id " + cityChallengeId + " does not exists"
        ));
        Technology technology = technologyRepository.findById(technologyId).orElseThrow(() -> new IllegalStateException(
                "technology with id " + technologyId + " does not exists"
        ));

        CityChallengeTechnologies cityChallengeTechnology = new CityChallengeTechnologies(cityChallenge, technologyRepository.findById(technologyId).get());
        cityChallenge.addCityChallengeTechnologies(cityChallengeTechnology);
        technology.addCityChallengeTechnologies(cityChallengeTechnology);

        cityChallengeTechnologyRepository.save(cityChallengeTechnology);
        technologyRepository.save(technology);
        app_cityChallengeRepository.save(cityChallenge);


    }

    public List<Technology> getTechnologiesFromCityChallenge(Long cityChallengeId) {
    	CityChallenge cityChallenge = app_cityChallengeRepository.findById(cityChallengeId).orElseThrow(() -> new IllegalStateException(
                "cityChallenge with id " + cityChallengeId + " does not exists"
        ));

        return cityChallenge.getCityChallengeTechnologies().stream().map(CityChallengeTechnologies::getTechnology).toList();
    }


    public List<String> getTagsFromCityChallenge(Long cityChallengeId) {
        return app_cityChallengeRepository.getTagsByCityChallengeId(cityChallengeId);
    }

    public Object[] getCityChallenge(Long cityChallengeId) {
        return app_cityChallengeRepository.findCityChallengeById(cityChallengeId);
    }

    public List<Object[]> getSubmittedCityChallenge(Long cityChallengeId) {
        return userCityChallengesRepository.getSubmittedCityChallenge(cityChallengeId);
    }
}