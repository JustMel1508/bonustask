package org.example.repository;

import org.example.exception.DatabaseOperationException;
import org.example.exception.ResourceNotFoundException;
import org.example.model.World;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorldRepository {
    private final JdbcTemplate jdbc;

    public WorldRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<World> findAll() {
        return jdbc.query(
                "SELECT id, name, universe_type FROM worlds ORDER BY id",
                (rs, rn) -> new World(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("universe_type")
                )
        );
    }

    public World findById(int id) {
        List<World> list = jdbc.query(
                "SELECT id, name, universe_type FROM worlds WHERE id = ?",
                (rs, rn) -> new World(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("universe_type")
                ),
                id
        );
        if (list.isEmpty()) throw new ResourceNotFoundException("World not found: " + id);
        return list.get(0);
    }

    public int create(String name, String universeType) {
        try {
            return jdbc.queryForObject(
                    "INSERT INTO worlds(name, universe_type) VALUES (?, ?) RETURNING id",
                    Integer.class,
                    name, universeType
            );
        } catch (Exception e) {
            throw new DatabaseOperationException("Failed to create world");
        }
    }
}
