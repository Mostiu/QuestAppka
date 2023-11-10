package com.example.backend.cityChallenge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityChallengeRepository extends JpaRepository<CityChallenge,Long> {

        @Query("SELECT s FROM CityChallenge s WHERE s.name = ?1")
        Optional<CityChallenge> findCityChallengeByName(String name);
}
