package com.example.backend.user_quests;

import com.example.backend.quest.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserQuestsRepository extends JpaRepository<UserQuests, Long> {

    @Query("SELECT uq.quest FROM UserQuests uq " +
            "WHERE uq.user.email = :userMail AND uq.quest.course.id = :courseId")
    List<Quest> findQuestsForUserAndCourse(@Param("userMail") String userMail, @Param("courseId") Long courseId);

    @Query("SELECT uq.comment FROM UserQuests uq " +
            "WHERE uq.user.email = :userMail AND uq.quest.course.id = :courseId AND uq.quest.id = :questId")
    String findUserCommentOnUserQuest(
            @Param("userMail") String userMail,
            @Param("courseId") Long courseId,
            @Param("questId") Long questId
    );


    @Modifying
    @Query("UPDATE UserQuests uq " +
            "SET uq.comment = :comment " +
            "WHERE uq.user.email = :userMail " +
            "AND uq.quest.id = :questId " +
            "AND uq.quest.course.id = :courseId")
    void updateUserCommentOnQuest(
            @Param("userMail") String userMail,
            @Param("courseId") Long courseId,
            @Param("questId") Long questId,
            @Param("comment") String comment
    );
}
