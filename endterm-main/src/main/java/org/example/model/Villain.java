package org.example.model;

public class Villain extends CharacterBase {
    public Villain(int id, String name, int powerLevel, int worldId) {
        super(id, name, powerLevel, worldId);
    }

    @Override
    public String getType() {
        return "VILLAIN";
    }
}
