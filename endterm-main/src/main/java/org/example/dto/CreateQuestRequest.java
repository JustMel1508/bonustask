package org.example.dto;

public class CreateQuestRequest {
    private String title;
    private int requiredPower;
    private String accessType;
    private int worldId;

    public String getTitle() { return title; }
    public int getRequiredPower() { return requiredPower; }
    public String getAccessType() { return accessType; }
    public int getWorldId() { return worldId; }

    public void setTitle(String title) { this.title = title; }
    public void setRequiredPower(int requiredPower) { this.requiredPower = requiredPower; }
    public void setAccessType(String accessType) { this.accessType = accessType; }
    public void setWorldId(int worldId) { this.worldId = worldId; }
}
