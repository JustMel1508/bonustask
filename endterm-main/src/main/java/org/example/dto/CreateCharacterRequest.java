package org.example.dto;

public class CreateCharacterRequest {
    private String name;
    private int powerLevel;
    private String type;
    private int worldId;

    public String getName() { return name; }
    public int getPowerLevel() { return powerLevel; }
    public String getType() { return type; }
    public int getWorldId() { return worldId; }

    public void setName(String name) { this.name = name; }
    public void setPowerLevel(int powerLevel) { this.powerLevel = powerLevel; }
    public void setType(String type) { this.type = type; }
    public void setWorldId(int worldId) { this.worldId = worldId; }
}
