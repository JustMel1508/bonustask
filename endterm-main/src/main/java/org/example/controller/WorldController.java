package org.example.controller;

import org.example.model.World;
import org.example.service.WorldService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/worlds")
public class WorldController {
    private final WorldService service;

    public WorldController(WorldService service) {
        this.service = service;
    }

    @GetMapping
    public List<World> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public World getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public World create(@RequestBody Map<String, String> body) {
        return service.create(body.get("name"), body.get("universeType"));
    }
}
