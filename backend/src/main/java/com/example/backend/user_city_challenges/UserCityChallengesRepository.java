package com.example.backend.user_city_challenges;

import com.example.backend.cityChallenge.CityChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCityChallengesRepository extends JpaRepository<UserCityChallenges, Long> {

    @Query("SELECT cc FROM CityChallenge cc " +
            "JOIN UserCityChallenges ucc ON cc.id = ucc.cityChallenge.id " +
            "JOIN App_User u ON u.id = ucc.user.id " +
            "WHERE u.email = :userEmail AND ucc.completed = true")
    List<CityChallenge> getCompletedCityChallengesByUserEmail(@Param("userEmail") String userEmail);

    @Query("SELECT ucc.comment FROM UserCityChallenges ucc " +
            "WHERE ucc.user.email = :userMail AND ucc.cityChallenge.id = :cityChallengeId")
    String findUserCommentOnCityChallenge(
            @Param("userMail") String userMail,
            @Param("cityChallengeId") Long cityChallengeId
    );

    @Modifying
    @Query(value = "UPDATE UserCityChallenges ucc " +
            "SET ucc.comment = :newComment " +
            "WHERE ucc.user.email = :userMail AND ucc.cityChallenge.id = :cityChallengeId")
    void updateUserCommentOnCityChallenge(
            @Param("userMail") String userMail,
            @Param("cityChallengeId") Long cityChallengeId,
            @Param("newComment") String newComment
    );



}
