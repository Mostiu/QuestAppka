package com.example.backend.quest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/quests")
public class QuestController {

    private final QuestService questService;

    @Autowired
    public QuestController(QuestService questService) {
        this.questService = questService;
    }

    @GetMapping
    public List<Quest> getQuests() {
        return questService.getQuests();
    }

    @PostMapping
    public void registerNewQuest(@RequestBody Quest quest) {
        questService.addNewQuest(quest);
    }

    @DeleteMapping(path="{questId}")
    public void deleteQuest(@PathVariable Long questId) {
        questService.deleteQuest(questId);
    }

    @PutMapping(path="{questId}")
    public void updateQuest(
            @PathVariable("questId") Long questId,
            @RequestParam(required=false) String name,
            @RequestParam(required=false) String description,
            @RequestParam(required=false) boolean is_completed,
            @RequestParam(required=false) String comment) {
        questService.updateQuest(questId, name, description, is_completed, comment);
        System.out.println("Quest updated");
    }


}
