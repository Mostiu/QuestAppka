package com.example.backend.user;


import com.example.backend.cityChallenge.CityChallenge;
import com.example.backend.course.Course;
import com.example.backend.quest.Quest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<App_User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path="{userMail}")
    public App_User getUser(@PathVariable String userMail) {
    	return userService.loadUserByUsername(userMail);
    }

    @PostMapping
    public void registerNewUser(@RequestBody App_User user) {
    	userService.addNewUser(user);
    }

    @DeleteMapping(path="{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
    	userService.deleteUser(userId);
    }

    @PutMapping(path="{userId}")
    public void updateUser(
    		@PathVariable("userId") Long userId,
    		@RequestParam(required=false) String name,
    		@RequestParam(required=false) String email) {
    	userService.updateUser(userId, name, email);
    }

    @PutMapping(path="{userId}/enroll/{courseId}")
    public void enrollUser(@PathVariable Long userId, @PathVariable Long courseId) {
    	userService.enrollUser(userId, courseId);
    }

    @PutMapping(path="{userId}/complete/quest/{questId}")
    public void completeQuest(@PathVariable Long userId, @PathVariable Long questId) {
    	userService.completeQuest(userId, questId);
    }

    @PutMapping(path="{userId}/complete/challenge/{cityChallengeId}")
    public void completeCityChallenge(@PathVariable Long userId, @PathVariable Long cityChallengeId) {
    	userService.completeCityChallenge(userId, cityChallengeId);
    }

    @GetMapping(path="{userMail}/courses")
    public List<Course> getUserCourses(@PathVariable String userMail) {
    	return userService.getUserCourses(userMail);
    }

    @GetMapping(path="{userMail}/cityChallenges")
    public List<CityChallenge> getUserCityChallenges(@PathVariable String userMail) {
    	return userService.getUserCityChallenges(userMail);
    }

    @GetMapping(path="{userMail}/course/{courseId}/quests")
    public List<Quest> getUserQuestsFromCourse(@PathVariable String userMail, @PathVariable Long courseId) {
    	return userService.getUserQuestsFromCourse(userMail, courseId);
    }
}
