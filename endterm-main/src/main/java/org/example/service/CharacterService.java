package org.example.service;

import org.example.dto.CreateCharacterRequest;
import org.example.exception.InvalidInputException;
import org.example.model.CharacterBase;
import org.example.patterns.factory.CharacterFactory;
import org.example.patterns.singleton.AppLogger;
import org.example.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {
    private final CharacterRepository repo;
    private final CacheService cacheService;
    private final CharacterFactory factory = new CharacterFactory();

    public CharacterService(CharacterRepository repo, CacheService cacheService) {
        this.repo = repo;
        this.cacheService = cacheService;
    }

    public List<CharacterBase> getAll() {
        return cacheService.getCache()
                .get(CacheService.CHARACTERS_ALL_KEY, List.class)
                .map(v -> (List<CharacterBase>) v)
                .orElseGet(() -> {
                    List<CharacterBase> fresh = repo.findAll();
                    cacheService.getCache().put(CacheService.CHARACTERS_ALL_KEY, List.copyOf(fresh));
                    return fresh;
                });
    }

    public CharacterBase getById(int id) {
        if (id <= 0) throw new InvalidInputException("id must be positive");
        return repo.findById(id);
    }

    public CharacterBase create(CreateCharacterRequest req) {
        CharacterBase c = factory.create(req.getType(), 0, req.getName(), req.getPowerLevel(), req.getWorldId());
        int id = repo.create(c);
        cacheService.invalidateCharactersAll();
        AppLogger.getInstance().info("Character created: name=" + req.getName() + " type=" + req.getType());
        return repo.findById(id);
    }

    public CharacterBase update(int id, CreateCharacterRequest req) {
        if (id <= 0) throw new InvalidInputException("id must be positive");
        CharacterBase c = factory.create(req.getType(), id, req.getName(), req.getPowerLevel(), req.getWorldId());
        repo.update(id, c);
        cacheService.invalidateCharactersAll();
        AppLogger.getInstance().info("Character updated: id=" + id);
        return repo.findById(id);
    }

    public void delete(int id) {
        if (id <= 0) throw new InvalidInputException("id must be positive");
        repo.delete(id);
        cacheService.invalidateCharactersAll();
        AppLogger.getInstance().info("Character deleted: id=" + id);
    }
}
