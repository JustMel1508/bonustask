package org.example.model;

public class Quest {
    private int id;
    private String title;
    private int requiredPower;
    private String accessType;
    private int worldId;

    private Quest(Builder b) {
        this.id = b.id;
        this.title = b.title;
        this.requiredPower = b.requiredPower;
        this.accessType = b.accessType;
        this.worldId = b.worldId;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getRequiredPower() { return requiredPower; }
    public String getAccessType() { return accessType; }
    public int getWorldId() { return worldId; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private int id;
        private String title;
        private int requiredPower;
        private String accessType;
        private int worldId;

        public Builder id(int id) { this.id = id; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder requiredPower(int requiredPower) { this.requiredPower = requiredPower; return this; }
        public Builder accessType(String accessType) { this.accessType = accessType; return this; }
        public Builder worldId(int worldId) { this.worldId = worldId; return this; }

        public Quest build() { return new Quest(this); }
    }
}
