package org.example.repository;

import org.example.exception.DatabaseOperationException;
import org.example.exception.ResourceNotFoundException;
import org.example.model.CharacterBase;
import org.example.patterns.factory.CharacterFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CharacterRepository {
    private final JdbcTemplate jdbc;
    private final CharacterFactory factory = new CharacterFactory();

    public CharacterRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<CharacterBase> findAll() {
        return jdbc.query(
                "SELECT id, name, power_level, type, world_id FROM characters ORDER BY id",
                (rs, rn) -> factory.create(
                        rs.getString("type"),
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("power_level"),
                        rs.getInt("world_id")
                )
        );
    }

    public CharacterBase findById(int id) {
        List<CharacterBase> list = jdbc.query(
                "SELECT id, name, power_level, type, world_id FROM characters WHERE id = ?",
                (rs, rn) -> factory.create(
                        rs.getString("type"),
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("power_level"),
                        rs.getInt("world_id")
                ),
                id
        );
        if (list.isEmpty()) throw new ResourceNotFoundException("Character not found: " + id);
        return list.get(0);
    }

    public int create(CharacterBase c) {
        c.validate();
        try {
            return jdbc.queryForObject(
                    "INSERT INTO characters(name, power_level, type, world_id) VALUES (?, ?, ?, ?) RETURNING id",
                    Integer.class,
                    c.getName(), c.getPowerLevel(), c.getType(), c.getWorldId()
            );
        } catch (Exception e) {
            throw new DatabaseOperationException("Failed to create character");
        }
    }

    public void update(int id, CharacterBase c) {
        c.validate();
        int updated = jdbc.update(
                "UPDATE characters SET name = ?, power_level = ?, type = ?, world_id = ? WHERE id = ?",
                c.getName(), c.getPowerLevel(), c.getType(), c.getWorldId(), id
        );
        if (updated == 0) throw new ResourceNotFoundException("Character not found: " + id);
    }

    public void delete(int id) {
        int deleted = jdbc.update("DELETE FROM characters WHERE id = ?", id);
        if (deleted == 0) throw new ResourceNotFoundException("Character not found: " + id);
    }
}
