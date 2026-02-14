package org.example.model;

import org.example.exception.InvalidInputException;

public abstract class CharacterBase {
    protected int id;
    protected String name;
    protected int powerLevel;
    protected int worldId;

    public CharacterBase(int id, String name, int powerLevel, int worldId) {
        this.id = id;
        this.name = name;
        this.powerLevel = powerLevel;
        this.worldId = worldId;
    }

    public abstract String getType();

    public int getId() { return id; }
    public String getName() { return name; }
    public int getPowerLevel() { return powerLevel; }
    public int getWorldId() { return worldId; }

    public void validate() {
        if (name == null || name.isBlank()) throw new InvalidInputException("Character name is empty");
        if (powerLevel < 1 || powerLevel > 100) throw new InvalidInputException("powerLevel must be 1..100");
        if (worldId <= 0) throw new InvalidInputException("worldId must be positive");
    }
}
