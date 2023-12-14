package com.example.backend;

import com.example.backend.cityChallenge.CityChallenge;
import com.example.backend.city_challenge_technologies.CityChallengeTechnologies;
import com.example.backend.user_city_challenges.UserCityChallenges;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class CityChallengeTests {

    @Test
    public void testCityChallenge() {

        String title = "Test Challenge";
        String description = "Test Description";
        String createdBy = "Test User";

        CityChallenge cityChallenge = new CityChallenge(title, description, createdBy);

        UserCityChallenges userCityChallenge = new UserCityChallenges();
        CityChallengeTechnologies cityChallengeTechnology = new CityChallengeTechnologies();


        cityChallenge.addUserCityChallenges(userCityChallenge);
        cityChallenge.addCityChallengeTechnologies(cityChallengeTechnology);


        assertNotNull(cityChallenge);
        assertEquals(title, cityChallenge.getTitle());
        assertEquals(description, cityChallenge.getDescription());
        assertEquals(createdBy, cityChallenge.getCreated_by());

        Set<UserCityChallenges> userCityChallenges = new HashSet<>();
        userCityChallenges.add(userCityChallenge);
        assertEquals(userCityChallenges, cityChallenge.getUserCityChallenges());

        Set<CityChallengeTechnologies> cityChallengeTechnologies = new HashSet<>();
        cityChallengeTechnologies.add(cityChallengeTechnology);
        assertEquals(cityChallengeTechnologies, cityChallenge.getCityChallengeTechnologies());
    }
}
