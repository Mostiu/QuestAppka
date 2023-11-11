package com.example.backend.user;


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

    @PutMapping(path="{userId}/complete/{questId}")
    public void completeQuest(@PathVariable Long userId, @PathVariable Long questId) {
    	userService.completeQuest(userId, questId);
    }
}
