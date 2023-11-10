package com.example.backend.quest;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestService {

    private final QuestRepository questRepository;

    @Autowired
    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public List<Quest> getQuests() {
        return questRepository.findAll();
    }

    public void addNewQuest(Quest quest) {
        questRepository.save(quest);
    }

    public void deleteQuest(Long questId) {
        boolean exists = questRepository.existsById(questId);
        if(!exists){
            throw new IllegalStateException("quest with id " + questId + " does not exists");
        }
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

        if(comment != null && comment.length() > 0 && !quest.getComment().equals(comment)){
            quest.setComment(comment);
        }
    }
}
