package org.example.service;

import org.example.dto.CreateQuestRequest;
import org.example.exception.InvalidInputException;
import org.example.model.CharacterBase;
import org.example.model.Quest;
import org.example.patterns.singleton.AppLogger;
import org.example.repository.CharacterRepository;
import org.example.repository.QuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestService {
    private final QuestRepository questRepo;
    private final CharacterRepository charRepo;

    public QuestService(QuestRepository questRepo, CharacterRepository charRepo) {
        this.questRepo = questRepo;
        this.charRepo = charRepo;
    }

    public List<Quest> getAll() {
        return questRepo.findAll();
    }

    public Quest create(CreateQuestRequest r) {
        if (r.getTitle() == null || r.getTitle().isBlank()) throw new InvalidInputException("title is required");
        if (r.getRequiredPower() < 1 || r.getRequiredPower() > 100) throw new InvalidInputException("requiredPower must be 1..100");
        if (r.getAccessType() == null || r.getAccessType().isBlank()) throw new InvalidInputException("accessType is required");
        if (r.getWorldId() <= 0) throw new InvalidInputException("worldId must be positive");

        Quest q = Quest.builder()
                .title(r.getTitle().trim())
                .requiredPower(r.getRequiredPower())
                .accessType(r.getAccessType().trim().toUpperCase())
                .worldId(r.getWorldId())
                .build();

        int id = questRepo.create(q);
        AppLogger.getInstance().info("Quest created: title=" + r.getTitle());
        return questRepo.findById(id);
    }

    public boolean tryQuest(int questId, int characterId) {
        Quest q = questRepo.findById(questId);
        CharacterBase c = charRepo.findById(characterId);

        boolean success = c.getPowerLevel() >= q.getRequiredPower()
                && q.getAccessType().equalsIgnoreCase(c.getType());

        AppLogger.getInstance().info("TryQuest questId=" + questId + " characterId=" + characterId + " success=" + success);
        return success;
    }
}
