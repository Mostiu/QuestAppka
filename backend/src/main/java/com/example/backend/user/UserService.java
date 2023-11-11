package com.example.backend.user;

import com.example.backend.cityChallenge.CityChallenge;
import com.example.backend.cityChallenge.CityChallengeRepository;
import com.example.backend.tag.course.Course;
import com.example.backend.tag.course.CourseRepository;
import com.example.backend.quest.Quest;
import com.example.backend.quest.QuestRepository;
import com.example.backend.user_city_challenges.UserCityChallenges;
import com.example.backend.user_city_challenges.UserCityChallengesRepository;
import com.example.backend.user_courses.UserCourses;
import com.example.backend.user_courses.UserCoursesRepository;
import com.example.backend.user_quests.UserQuests;
import com.example.backend.user_quests.UserQuestsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository app_userRepository;

    private final CourseRepository courseRepository;

    private final UserCoursesRepository userCoursesRepository;

    private final UserQuestsRepository userQuestsRepository;

    private final QuestRepository questRepository;

    private final CityChallengeRepository cityChallengeRepository;

    private final UserCityChallengesRepository userCityChallengeRepository;

    @Autowired
    public UserService(UserRepository app_userRepository, CourseRepository courseRepository, UserCoursesRepository userCoursesRepository,
                       UserQuestsRepository userQuestsRepository, QuestRepository questRepository, CityChallengeRepository cityChallengeRepository,
                       UserCityChallengesRepository userCityChallengeRepository) {
        this.app_userRepository = app_userRepository;
        this.courseRepository = courseRepository;
        this.userCoursesRepository = userCoursesRepository;
        this.userQuestsRepository = userQuestsRepository;
        this.questRepository = questRepository;
        this.cityChallengeRepository = cityChallengeRepository;
        this.userCityChallengeRepository = userCityChallengeRepository;
    }

    public List<App_User> getUsers() {
        return app_userRepository.findAll();
    }

    public void addNewUser(App_User user) {
        Optional<App_User> userOptional = app_userRepository.findUserByEmail(user.getEmail());

        if(userOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        app_userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = app_userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("user with id " + userId + " does not exists");
        }
        app_userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String name, String email) {
        App_User user = app_userRepository.findById(userId).orElseThrow(() -> new IllegalStateException(
                "user with id " + userId + " does not exists"
        ));

        if(name != null && name.length() > 0 && !user.getName().equals(name)){
            user.setName(name);
        }

        if(email != null && email.length() > 0 && !user.getEmail().equals(email)){
            Optional<App_User> userOptional = app_userRepository.findUserByEmail(email);
            if(userOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            user.setEmail(email);
        }
    }

    @Transactional
    public void enrollUser(Long userId, Long courseId) {
        App_User user = app_userRepository.findById(userId).orElseThrow(() -> new IllegalStateException(
                "user with id " + userId + " does not exists"
        ));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalStateException(
                "course with id " + courseId + " does not exists"
        ));

        UserCourses userCourses = new UserCourses();
        userCourses.setUser(user);
        userCourses.setCourse(course);
        userCourses.setCompleted(false);
        userCourses.setComment("");

        user.addUserCourses(userCourses);
        course.addUserCourses(userCourses);

        userCoursesRepository.save(userCourses);
        app_userRepository.save(user);
        courseRepository.save(course);

    }

    @Transactional
    public void completeQuest(Long userId, Long questId) {
        App_User user = app_userRepository.findById(userId).orElseThrow(() -> new IllegalStateException(
                "user with id " + userId + " does not exists"
        ));
        Quest quest = questRepository.findById(questId).orElseThrow(() -> new IllegalStateException(
                "quest with id " + questId + " does not exists"
        ));

        UserQuests userQuests = new UserQuests();
        userQuests.setUser(user);
        userQuests.setQuest(quest);
        userQuests.setCompleted(true);
        userQuests.setComment("");

        user.addUserQuests(userQuests);
        quest.addUserQuests(userQuests);

        userQuestsRepository.save(userQuests);
        app_userRepository.save(user);
        questRepository.save(quest);
    }

    @Transactional
    public void completeCityChallenge(Long userId, Long cityChallengeId) {
        App_User user = app_userRepository.findById(userId).orElseThrow(() -> new IllegalStateException(
                "user with id " + userId + " does not exists"
        ));
        CityChallenge cityChallenge = cityChallengeRepository.findById(cityChallengeId).orElseThrow(() -> new IllegalStateException(
                "cityChallenge with id " + cityChallengeId + " does not exists"
        ));

        UserCityChallenges userCityChallenge = new UserCityChallenges();
        userCityChallenge.setUser(user);
        userCityChallenge.setCityChallenge(cityChallenge);
        userCityChallenge.setCompleted(false);

        user.addUserCityChallenges(userCityChallenge);
        cityChallenge.addUserCityChallenges(userCityChallenge);

        userCityChallengeRepository.save(userCityChallenge);
        app_userRepository.save(user);
        cityChallengeRepository.save(cityChallenge);

    }
}
