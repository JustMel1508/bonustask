package org.example.patterns.factory;

import org.example.exception.InvalidInputException;
import org.example.model.CharacterBase;
import org.example.model.Hero;
import org.example.model.Villain;

public class CharacterFactory {

    public CharacterBase create(String type, int id, String name, int powerLevel, int worldId) {
        if (type == null || type.isBlank()) throw new InvalidInputException("type is required");

        return switch (type.toUpperCase()) {
            case "HERO" -> new Hero(id, name, powerLevel, worldId);
            case "VILLAIN" -> new Villain(id, name, powerLevel, worldId);
            default -> throw new InvalidInputException("Unknown character type: " + type);
        };
    }
}
