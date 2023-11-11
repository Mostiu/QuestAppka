package com.example.backend.user_quests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuestsRepository extends JpaRepository<UserQuests, Long> {
}
