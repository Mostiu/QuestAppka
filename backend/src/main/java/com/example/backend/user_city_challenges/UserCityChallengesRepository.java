package com.example.backend.user_city_challenges;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCityChallengesRepository extends JpaRepository<UserCityChallenges, Long> {
}
