package com.yaqubmw.mynote.repository;

import com.yaqubmw.mynote.model.Note;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MyNoteRepository {
    private final JdbcTemplate jdbcTemplate;
    
    public MyNoteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createNote(Note note) {
        String query = "INSERT INTO mynote (title, content) VALUES (?, ?)";
        jdbcTemplate.update(query, note.getTitle(), note.getContent());
    }

    public List<Note> getAllNotes() {
        String query = "SELECT id, title, content FROM mynote";
        return jdbcTemplate.query(query, this::mapRowNote);
    }

    public Note getNoteById(Long id) {
        String query = "SELECT id, title, content FROM mynote WHERE id = ?";
        return jdbcTemplate.queryForObject(query, this::mapRowNote, id);
    }

    public void updateNote(Note note) {
        String query = "UPDATE mynote SET title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(query, note.getTitle(), note.getContent(), note.getId());
    }

    public void deleteNote(Long id) {
        String query = "DELETE FROM mynote WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    private Note mapRowNote(ResultSet rs, int rowNum) throws SQLException {
        Note note = new Note();
        note.setId(rs.getLong("id"));
        note.setTitle(rs.getString("title"));
        note.setContent(rs.getString("content"));
        return note;
    }
}
