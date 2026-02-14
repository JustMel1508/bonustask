package org.example.controller;

import org.example.dto.CreateCharacterRequest;
import org.example.model.CharacterBase;
import org.example.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {
    private final CharacterService service;

    public CharacterController(CharacterService service) {
        this.service = service;
    }

    @GetMapping
    public List<CharacterBase> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CharacterBase getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CharacterBase create(@RequestBody CreateCharacterRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}")
    public CharacterBase update(@PathVariable int id, @RequestBody CreateCharacterRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
