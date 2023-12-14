package com.example.backend;

import com.example.backend.city_challenge_technologies.CityChallengeTechnologies;
import com.example.backend.cityChallenge.CityChallenge;
import com.example.backend.technology.Technology;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CityChallengeTechnologiesTests {

    @Test
    public void testCityChallengeTechnologies() {

        CityChallenge cityChallenge = new CityChallenge();
        Technology technology = new Technology();


        CityChallengeTechnologies cityChallengeTechnologies = new CityChallengeTechnologies(cityChallenge, technology);


        assertNotNull(cityChallengeTechnologies);
        assertEquals(cityChallenge, cityChallengeTechnologies.getCityChallenge());
        assertEquals(technology, cityChallengeTechnologies.getTechnology());
    }
}
