package com.example.backend.quest;

import com.example.backend.course.CourseRepository;
import com.example.backend.user.App_User;
import com.example.backend.user_quests.UserQuests;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class QuestService {

    private final QuestRepository questRepository;

    private final CourseRepository courseRepository;

    @Autowired
    public QuestService(QuestRepository questRepository, CourseRepository courseRepository) {
        this.questRepository = questRepository;
        this.courseRepository = courseRepository;
    }

    public List<Quest> getQuests() {
        return questRepository.findAll();
    }

    public void addNewQuest(Quest quest) {
        questRepository.save(quest);
    }

    @Transactional
    public void deleteQuest(Long questId) {
        boolean exists = questRepository.existsById(questId);
        if(!exists){
            throw new IllegalStateException("quest with id " + questId + " does not exists");
        }

        //disassociate Quest from UserQuests
        Quest q = questRepository.findById(questId).orElseThrow(() -> new IllegalStateException(
                "quest with id " + questId + " does not exists"
        ));

        Set<UserQuests> userQuests = q.getUserQuests();
        if(userQuests != null && !((Set<?>) userQuests).isEmpty()){
            for(UserQuests userQuest : userQuests){
                App_User user = userQuest.getUser();
                if(user != null){
                    user.getUserQuests().remove(userQuest);
                }
                userQuest.setUser(null);
            }
            userQuests.clear();
        }

        q.getCourse().getQuests().remove(q);
        
        questRepository.deleteById(questId);
    }

    @Transactional
    public void updateQuest(Long questId, String name, String description, boolean is_completed, String comment) {
        Quest quest = questRepository.findById(questId).orElseThrow(() -> new IllegalStateException(
                "quest with id " + questId + " does not exists"
        ));

        if(name != null && name.length() > 0 && !quest.getName().equals(name)){
            quest.setName(name);
        }

        if(description != null && description.length() > 0 && !quest.getDescription().equals(description)){
            quest.setDescription(description);
        }

        if(is_completed != quest.isIs_completed()){
            quest.setIs_completed(is_completed);
        }

    }

}
