package com.example.backend.course;


import com.example.backend.quest.Quest;
import com.example.backend.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Object[]> getCourses() {
        return courseService.getCourses();
    }

    @PostMapping
    public void registerNewCourse(@RequestBody Course course) {
        courseService.addNewCourse(course);
    }

    @DeleteMapping(path="{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
    }

    @PutMapping(path="{courseId}")
    public void updateCourse(
            @PathVariable("courseId") Long courseId,
            @RequestParam(required=false) String title,
            @RequestParam(required=false) String description,
            @RequestParam(required=false) Difficulty difficulty) {
        courseService.updateCourse(courseId, title, description, difficulty);
        System.out.println("Course updated");
    }

    @PutMapping(path="{courseId}/add_technology/{technologyId}")
    public void addTechnologyToCourse(
            @PathVariable("courseId") Long courseId,
            @PathVariable("technologyId") Long technologyId) {
        courseService.addTechnologyToCourse(courseId, technologyId);
        System.out.println("Technology added to course");
    }

    @PutMapping(path="{courseId}/add_quest/{questId}")
    public void addQuestToCourse(
            @PathVariable("courseId") Long courseId,
            @PathVariable("questId") Long questId) {
        courseService.addQuestToCourse(courseId, questId);
        System.out.println("Quest added to course");
    }

    @GetMapping(path="{courseId}/tags")
    public List<String> getTagsFromCourse(@PathVariable Long courseId) {
    	return courseService.getTagsFromCourse(courseId);
    }

    @GetMapping(path="{courseId}/quests")
    public List<Quest> getQuestsFromCourse(Long courseId) {
        return courseService.getQuestsFromCourse(courseId);
    }
}
