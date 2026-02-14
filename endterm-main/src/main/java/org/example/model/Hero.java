package org.example.model;

public class Hero extends CharacterBase {
    public Hero(int id, String name, int powerLevel, int worldId) {
        super(id, name, powerLevel, worldId);
    }

    @Override
    public String getType() {
        return "HERO";
    }
}
