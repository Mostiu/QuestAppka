package com.example.backend.cityChallenge;

import com.example.backend.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityChallengeRepository extends JpaRepository<CityChallenge,Long> {

        @Query("SELECT s FROM CityChallenge s WHERE s.title = ?1")
        Optional<CityChallenge> findCityChallengeByTitle(String title);

        @Query("SELECT tag.name " +
                "FROM Technology t " +
                "JOIN TechnologyTags tt ON t.id = tt.technology.id " +
                "JOIN Tag tag ON tt.tag.id = tag.id " +
                "JOIN CityChallengeTechnologies cct ON t.id = cct.technology.id " +
                "WHERE cct.cityChallenge.id = :cityChallengeId")
        List<String> getTagsByCityChallengeId(@Param("cityChallengeId") Long cityChallengeId);


        @Query("SELECT id, title, description FROM CityChallenge")
        List<Object[]> getCityChallengesInfo();
}
