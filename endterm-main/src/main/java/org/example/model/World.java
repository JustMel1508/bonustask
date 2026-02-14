package org.example.model;

public class World {
    private int id;
    private String name;
    private String universeType;

    public World(int id, String name, String universeType) {
        this.id = id;
        this.name = name;
        this.universeType = universeType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUniverseType() {
        return universeType;
    }
}
