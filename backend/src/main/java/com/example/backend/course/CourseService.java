package com.example.backend.course;


import com.example.backend.course_technologies.CourseTechnologies;
import com.example.backend.course_technologies.CourseTechnologiesRepository;
import com.example.backend.quest.Quest;
import com.example.backend.quest.QuestRepository;
import com.example.backend.tag.Tag;
import com.example.backend.technology.Technology;
import com.example.backend.technology.TechnologyRepository;
import com.example.backend.user.App_User;
import com.example.backend.user_courses.UserCourses;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TechnologyRepository technologyRepository;

    private final CourseTechnologiesRepository courseTechnologiesRepository;

    private final QuestRepository questRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, TechnologyRepository technologyRepository, CourseTechnologiesRepository courseTechnologiesRepository, QuestRepository questRepository) {
        this.courseRepository = courseRepository;
        this.technologyRepository = technologyRepository;
        this.courseTechnologiesRepository = courseTechnologiesRepository;
        this.questRepository = questRepository;
    }

    public List<Object[]> getCourses() {
        return courseRepository.getCoursesInfo();
    }

    public void addNewCourse(Course course) {
        courseRepository.save(course);
    }

    @Transactional
    public void addNewCourse(CourseAddBody courseAddBody) {
        String title = courseAddBody.getTitle();
        String description = courseAddBody.getDescription();
        List<Long> technologies = courseAddBody.getTechnologies();
        List<String> quests = courseAddBody.getQuests();



        Course course = new Course(title, description, Difficulty.EASY);
        courseRepository.save(course);
        Set<CourseTechnologies> courseTechnologiesList = new HashSet<>();
        Set<Quest> questList = new HashSet<>();

        for (Long technology : technologies) {
            Technology t = technologyRepository.findById(technology).orElseThrow(() -> new IllegalStateException(
                    "technology with id " + technology + " does not exists"
            ));
            CourseTechnologies courseTechnologies = new CourseTechnologies(course, t);
            courseTechnologiesList.add(courseTechnologies);

        }

        for (String quest : quests) {
            Quest q = new Quest("Q" + quest.charAt(1), quest, false, course);
            questList.add(q);
        }

        course.setQuests(questList);
        course.setCourseTechnologies(courseTechnologiesList);
        courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) {
        boolean exists = courseRepository.existsById(courseId);
        if(!exists){
            throw new IllegalStateException("course with id " + courseId + " does not exists");
        }

        Course c = courseRepository.findById(courseId).orElseThrow(() -> new IllegalStateException(
                "course with id " + courseId + " does not exists"
        ));

        //disassociate CourseTechnologies from Technology
        Set<CourseTechnologies> courseTechnologies = c.getCourseTechnologies();
        if(courseTechnologies != null && !((Set<?>) courseTechnologies).isEmpty()){
            for(CourseTechnologies courseTechnology : courseTechnologies){
                Technology technology = courseTechnology.getTechnology();
                if(technology != null){
                    technology.getCourseTechnologies().remove(courseTechnology);
                }
                courseTechnology.setTechnology(null);
            }
            courseTechnologies.clear();
        }

        //disassociate UserCourses from App_User
        Set<UserCourses> userCourses = c.getUserCourses();
        if(userCourses != null && !((Set<?>) userCourses).isEmpty()){
            for(UserCourses userCourse : userCourses){
                App_User user = userCourse.getUser();
                if(user != null){
                    user.getUserCourses().remove(userCourse);
                }
                userCourse.setUser(null);
            }
            userCourses.clear();
        }


        courseRepository.deleteById(courseId);
    }

    @Transactional
    public void updateCourse(Long courseId, String title, String description, Difficulty difficulty) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalStateException(
                "course with id " + courseId + " does not exists"
        ));

        if(title != null && title.length() > 0 && !course.getTitle().equals(title)){
            course.setTitle(title);
        }

        if(description != null && description.length() > 0 && !course.getDescription().equals(description)){
            course.setDescription(description);
        }

        if(difficulty != null  && !course.getDifficulty().equals(difficulty)){
            course.setDifficulty(difficulty);
        }
    }

    @Transactional
    public void addTechnologyToCourse(Long courseId, Long technologyId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalStateException(
                "course with id " + courseId + " does not exists"
        ));

        Technology technology = technologyRepository.findById(technologyId).orElseThrow(() -> new IllegalStateException(
                "technology with id " + technologyId + " does not exists"
        ));

        CourseTechnologies courseTechnologies = new CourseTechnologies(course, technology);

        courseRepository.save(course);
        technologyRepository.save(technology);
        courseTechnologiesRepository.save(courseTechnologies);
    }

    public void addQuestToCourse(Long courseId, Long questId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalStateException(
                "course with id " + courseId + " does not exists"
        ));

        Quest quest = questRepository.findById(questId).orElseThrow(() -> new IllegalStateException(
                "quest with id " + questId + " does not exists"
        ));


        course.addQuests(quest);
        quest.setCourse(course);
        courseRepository.save(course);
        questRepository.save(quest);
    }

    public List<String> getTagsFromCourse(Long courseId) {
        return courseRepository.getTagsByCourseId(courseId);
    }

    public List<Technology> getTechnologiesFromCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalStateException(
                "course with id " + courseId + " does not exists"
        ));

        return course.getCourseTechnologies().stream().map(CourseTechnologies::getTechnology).toList();

    }

    public List<Quest> getQuestsFromCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalStateException(
                "course with id " + courseId + " does not exists"
        ));

        return course.getQuestsFromCourse().stream().toList();
    }
}
