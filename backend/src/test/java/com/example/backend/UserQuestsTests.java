package com.example.backend;

import com.example.backend.quest.Quest;
import com.example.backend.user.App_User;
import com.example.backend.user_quests.UserQuests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserQuestsTests {

    @Mock
    private App_User user;

    @Mock
    private Quest quest;

    @InjectMocks
    private UserQuests userQuests;

    @Test
    void testGettersAndSetters() {
        // Set up
        userQuests.setId(1L);
        userQuests.setUser(user);
        userQuests.setQuest(quest);
        userQuests.setCompleted(true);
        userQuests.setComment("Quest completed!");

        // Verify
        assertEquals(1L, userQuests.getId());
        assertEquals(user, userQuests.getUser());
        assertEquals(quest, userQuests.getQuest());
        assertEquals(true, userQuests.isCompleted());
        assertEquals("Quest completed!", userQuests.getComment());
    }

    @Test
    void testToString() {
        // Set up
        userQuests.setId(1L);
        userQuests.setUser(user);
        userQuests.setQuest(quest);
        userQuests.setCompleted(true);
        userQuests.setComment("Quest completed!");

        // Execute
        String result = userQuests.toString();

        // Verify
        assertEquals("UserQuests{id=1, user=" + user + ", quest=" + quest + ", completed=true, comment='Quest completed!'}", result);
    }
}

