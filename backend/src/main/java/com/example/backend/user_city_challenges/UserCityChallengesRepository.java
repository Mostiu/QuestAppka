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

    @Query("SELECT cc.id, cc.title, cc.description FROM CityChallenge cc " +
            "JOIN UserCityChallenges ucc ON cc.id = ucc.cityChallenge.id " +
            "JOIN App_User u ON u.id = ucc.user.id " +
            "WHERE u.email = :userEmail AND ucc.completed = true")
    List<Object[]> getCompletedCityChallengesByUserEmail(@Param("userEmail") String userEmail);

    @Query("SELECT cc.id, cc.title, cc.description, ucc.comment " +
            "FROM UserCityChallenges ucc " +
            "JOIN ucc.cityChallenge cc " +
            "WHERE ucc.user.email = :userMail AND cc.id = :cityChallengeId ")
    Object[] findUserCommentOnCityChallenge(
            @Param("userMail") String userMail,
            @Param("cityChallengeId") Long cityChallengeId
    );

    @Modifying
    @Query(value = "UPDATE UserCityChallenges ucc " +
            "SET ucc.comment = :newComment, ucc.completed = true " +
            "WHERE ucc.user.email = :userMail AND ucc.cityChallenge.id = :cityChallengeId")
    void updateUserCommentOnCityChallenge(
            @Param("userMail") String userMail,
            @Param("cityChallengeId") Long cityChallengeId,
            @Param("newComment") String newComment
    );


    @Query("SELECT ucc.user.name, ucc.comment " +
            "FROM UserCityChallenges ucc " +
            "JOIN ucc.cityChallenge cc " +
            "WHERE cc.id = :cityChallengeId AND ucc.completed = true")
    List<Object[]> getSubmittedCityChallenge(Long cityChallengeId);
}
