package com.example.backend;

import com.example.backend.course.Course;
import com.example.backend.quest.Quest;
import com.example.backend.user_quests.UserQuests;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class QuestTests {

    @Test
    void testQuest() {
        // Arrange
        String name = "Test Quest";
        String description = "Test Description";
        boolean isCompleted = false;
        String comment = "Test Comment";

        Quest quest = new Quest(name, description, isCompleted, comment);

        Course course = new Course(/* inicjalizacja obiektu Course */);
        UserQuests userQuests = new UserQuests(/* inicjalizacja obiektu UserQuests */);

        // Act
        quest.setCourse(course);
        quest.addUserQuests(userQuests);

        // Assert
        assertNotNull(quest);
        assertEquals(name, quest.getName());
        assertEquals(description, quest.getDescription());
        assertEquals(isCompleted, quest.isIs_completed());
        assertEquals(comment, quest.getComment());
        assertEquals(course, quest.getCourse());

        Set<UserQuests> userQuestsSet = new HashSet<>();
        userQuestsSet.add(userQuests);
        assertEquals(userQuestsSet, quest.getUserQuests());
    }
}

