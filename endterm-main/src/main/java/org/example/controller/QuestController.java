package org.example.controller;

import org.example.dto.CreateQuestRequest;
import org.example.model.Quest;
import org.example.service.QuestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quests")
public class QuestController {
    private final QuestService service;

    public QuestController(QuestService service) {
        this.service = service;
    }

    @GetMapping
    public List<Quest> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Quest create(@RequestBody CreateQuestRequest req) {
        return service.create(req);
    }

    @PostMapping("/{questId}/try")
    public Map<String, Object> tryQuest(@PathVariable int questId, @RequestParam int characterId) {
        boolean success = service.tryQuest(questId, characterId);
        return Map.of("questId", questId, "characterId", characterId, "success", success);
    }
}
