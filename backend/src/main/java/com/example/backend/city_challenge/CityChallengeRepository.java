package com.example.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityChallengeRepository extends JpaRepository<App_User,Long> {

  @Query("SELECT s FROM App_CityChallenge s WHERE s.name = ?1")
  Optional<App_CityChallenge> findCityChallengeByName(String name);
}
