package org.example.repository;

import org.example.exception.DatabaseOperationException;
import org.example.exception.ResourceNotFoundException;
import org.example.model.Quest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestRepository {
    private final JdbcTemplate jdbc;

    public QuestRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Quest> findAll() {
        return jdbc.query(
                "SELECT id, title, required_power, access_type, world_id FROM quests ORDER BY id",
                (rs, rn) -> Quest.builder()
                        .id(rs.getInt("id"))
                        .title(rs.getString("title"))
                        .requiredPower(rs.getInt("required_power"))
                        .accessType(rs.getString("access_type"))
                        .worldId(rs.getInt("world_id"))
                        .build()
        );
    }

    public Quest findById(int id) {
        List<Quest> list = jdbc.query(
                "SELECT id, title, required_power, access_type, world_id FROM quests WHERE id = ?",
                (rs, rn) -> Quest.builder()
                        .id(rs.getInt("id"))
                        .title(rs.getString("title"))
                        .requiredPower(rs.getInt("required_power"))
                        .accessType(rs.getString("access_type"))
                        .worldId(rs.getInt("world_id"))
                        .build(),
                id
        );
        if (list.isEmpty()) throw new ResourceNotFoundException("Quest not found: " + id);
        return list.get(0);
    }

    public int create(Quest q) {
        try {
            return jdbc.queryForObject(
                    "INSERT INTO quests(title, required_power, access_type, world_id) VALUES (?,?,?,?) RETURNING id",
                    Integer.class,
                    q.getTitle(), q.getRequiredPower(), q.getAccessType(), q.getWorldId()
            );
        } catch (Exception e) {
            throw new DatabaseOperationException("Failed to create quest");
        }
    }
}
