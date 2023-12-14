package com.example.backend;

import com.example.backend.user.App_User;
import com.example.backend.user.Role;
import com.example.backend.user_city_challenges.UserCityChallenges;
import com.example.backend.user_courses.UserCourses;
import com.example.backend.user_quests.UserQuests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserTests {

    @Mock
    private UserCourses userCourses;

    @Mock
    private UserQuests userQuests;

    @Mock
    private UserCityChallenges userCityChallenges;

    @InjectMocks
    private App_User appUser;

    @Test
    void testGettersAndSetters() {
        // Set up
        appUser.setId(1L);
        appUser.setName("John Doe");
        appUser.setEmail("john.doe@example.com");
        appUser.setPassword("password");
        appUser.setUserCourses(Set.of(userCourses));
        appUser.setUserQuests(Set.of(userQuests));
        appUser.setUserCityChallenges(Set.of(userCityChallenges));
        appUser.setRole(Role.USER);

        // Verify
        assertEquals(1L, appUser.getId());
        assertEquals("John Doe", appUser.getName());
        assertEquals("john.doe@example.com", appUser.getEmail());
        assertEquals("password", appUser.getPassword());
        assertEquals(Set.of(userCourses), appUser.getUserCourses());
        assertEquals(Set.of(userQuests), appUser.getUserQuests());
        assertEquals(Set.of(userCityChallenges), appUser.getUserCityChallenges());
        assertEquals(Role.USER, appUser.getRole());
    }

    @Test
    void testAddUserCourses() {
        // Set up
        Set<UserCourses> userCoursesSet = new HashSet<>();
        lenient().when(userCourses.getId()).thenReturn(1L);

        // Execute
        appUser.addUserCourses(userCourses);

        // Verify
        assertEquals(Set.of(userCourses), appUser.getUserCourses());
    }

    @Test
    void testAddUserQuests() {
        // Set up
        Set<UserQuests> userQuestsSet = new HashSet<>();
        lenient().when(userQuests.getId()).thenReturn(1L);

        // Execute
        appUser.addUserQuests(userQuests);

        // Verify
        assertEquals(Set.of(userQuests), appUser.getUserQuests());
    }

    @Test
    void testAddUserCityChallenges() {
        // Set up
        Set<UserCityChallenges> userCityChallengesSet = new HashSet<>();
        lenient().when(userCityChallenges.getId()).thenReturn(1L);

        // Execute
        appUser.addUserCityChallenges(userCityChallenges);

        // Verify
        assertEquals(Set.of(userCityChallenges), appUser.getUserCityChallenges());
    }

    @Test
    void testRemoveUserCityChallenges() {
        // Set up
        Set<UserCityChallenges> userCityChallengesSet = new HashSet<>();
        lenient().when(userCityChallenges.getId()).thenReturn(1L);
        appUser.setUserCityChallenges(userCityChallengesSet);

        // Execute
        appUser.removeUserCityChallenges(userCityChallenges);

        // Verify
        assertEquals(Set.of(), appUser.getUserCityChallenges());
    }
}
