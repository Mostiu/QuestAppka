package com.example.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<App_User,Long> {

    @Query("SELECT s FROM App_User s WHERE s.email = ?1")
    Optional<App_User> findUserByEmail(String email);
}
