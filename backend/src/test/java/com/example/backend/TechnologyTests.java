package com.example.backend;

import com.example.backend.city_challenge_technologies.CityChallengeTechnologies;
import com.example.backend.course_technologies.CourseTechnologies;
import com.example.backend.technology.Technology;
import com.example.backend.technology_tags.TechnologyTags;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class TechnologyTests {

    @Test
    public void testTechnologyCreation() {
        // Given
        String technologyName = "Java";
        Technology technology = new Technology(technologyName);

        // When
        String retrievedName = technology.getName();

        // Then
        Assertions.assertEquals(technologyName, retrievedName);
    }

    @Test
    public void testAddCourseTechnologies() {
        // Given
        Technology technology = new Technology("Java");
        CourseTechnologies courseTech1 = new CourseTechnologies();
        CourseTechnologies courseTech2 = new CourseTechnologies();

        // When
        technology.addCourseTechnologies(courseTech1);
        technology.addCourseTechnologies(courseTech2);

        // Then
        Set<CourseTechnologies> courseTechs = technology.getCourseTechnologies();
        Assertions.assertEquals(2, courseTechs.size());
        Assertions.assertTrue(courseTechs.contains(courseTech1));
        Assertions.assertTrue(courseTechs.contains(courseTech2));
    }

    @Test
    public void testRemoveCityChallengeTechnologies() {
        // Given
        Technology technology = new Technology("Java");
        CityChallengeTechnologies cityChallengeTech1 = new CityChallengeTechnologies();
        CityChallengeTechnologies cityChallengeTech2 = new CityChallengeTechnologies();
        technology.addCityChallengeTechnologies(cityChallengeTech1);
        technology.addCityChallengeTechnologies(cityChallengeTech2);

        // When
        technology.removeCityChallengeTechnologies(cityChallengeTech1);

        // Then
        Set<CityChallengeTechnologies> cityChallengeTechs = technology.getCityChallengeTechnologies();
        Assertions.assertEquals(1, cityChallengeTechs.size());
        Assertions.assertFalse(cityChallengeTechs.contains(cityChallengeTech1));
        Assertions.assertTrue(cityChallengeTechs.contains(cityChallengeTech2));
    }

    @Test
    public void testAddTechnologyTags() {
        // Given
        Technology technology = new Technology("Java");
        TechnologyTags techTag1 = new TechnologyTags();
        TechnologyTags techTag2 = new TechnologyTags();

        // When
        technology.addTechnologyTags(techTag1);
        technology.addTechnologyTags(techTag2);

        // Then
        Set<TechnologyTags> techTags = technology.getTechnologyTags();
        Assertions.assertEquals(2, techTags.size());
        Assertions.assertTrue(techTags.contains(techTag1));
        Assertions.assertTrue(techTags.contains(techTag2));
    }

    @Test
    public void testRemoveTechnologyTags() {
        // Given
        Technology technology = new Technology("Java");
        TechnologyTags techTag1 = new TechnologyTags();
        TechnologyTags techTag2 = new TechnologyTags();
        technology.addTechnologyTags(techTag1);
        technology.addTechnologyTags(techTag2);

        // When
        technology.getTechnologyTags().remove(techTag1);

        // Then
        Set<TechnologyTags> techTags = technology.getTechnologyTags();
        Assertions.assertEquals(1, techTags.size());
        Assertions.assertFalse(techTags.contains(techTag1));
        Assertions.assertTrue(techTags.contains(techTag2));
    }

    @Test
    public void testTechnologyToString() {
        // Given
        Technology technology = new Technology("Java");

        // When
        String techString = technology.toString();

        // Then
        Assertions.assertTrue(techString.contains("id=" + technology.getId()));
        Assertions.assertTrue(techString.contains("name='Java'"));
        Assertions.assertTrue(techString.contains("courseTechnologies=" + technology.getCourseTechnologies()));
        Assertions.assertTrue(techString.contains("cityChallengeTechnologies=" + technology.getCityChallengeTechnologies()));
        Assertions.assertTrue(techString.contains("technologyTags=" + technology.getTechnologyTags()));
    }
}

