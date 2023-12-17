package com.example.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<App_User,Long> {

    @Query("SELECT s FROM App_User s WHERE s.email = ?1")
    Optional<App_User> findUserByEmail(String email);


    @Modifying
    @Query("INSERT INTO UserCourses (user, course, comment, completed) " +
            "SELECT :user, c, null, false FROM Course c")
    void addUserToAllCourses(@Param("user") App_User user);

    @Modifying
    @Query("INSERT INTO UserQuests (user, quest, comment, completed) " +
            "SELECT :user, q, null, false FROM Quest q")
    void addUserToAllQuests(@Param("user") App_User user);


}

