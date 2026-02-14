package org.example.service;

import org.example.exception.InvalidInputException;
import org.example.model.World;
import org.example.repository.WorldRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorldService {
    private final WorldRepository repo;

    public WorldService(WorldRepository repo) {
        this.repo = repo;
    }

    public List<World> getAll() {
        return repo.findAll();
    }

    public World getById(int id) {
        if (id <= 0) throw new InvalidInputException("id must be positive");
        return repo.findById(id);
    }

    public World create(String name, String universeType) {
        if (name == null || name.isBlank()) throw new InvalidInputException("name is required");
        if (universeType == null || universeType.isBlank()) throw new InvalidInputException("universeType is required");

        int id = repo.create(name.trim(), universeType.trim());
        return repo.findById(id);
    }
}
