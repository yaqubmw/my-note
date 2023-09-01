package com.yaqubmw.mynote.controller;

import com.yaqubmw.mynote.model.Note;
import com.yaqubmw.mynote.service.MyNoteService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class MyNoteController {
    private final MyNoteService myNoteService;

    public MyNoteController(MyNoteService myNoteService) {
        this.myNoteService = myNoteService;
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return myNoteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Long id) {
        return myNoteService.getNoteById(id);
    }

    @PostMapping
    public String createNote(@RequestBody Note note) {
        myNoteService.createNote(note);
        return "Note created successfully.";
    }

    @PutMapping("/{id}")
    public String updateNoteById(@PathVariable Long id, @RequestBody Note updatedNote) {
        Note existingNote = myNoteService.getNoteById(id);
        if (existingNote == null) {
            return "Note not found.";
        }

        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());

        myNoteService.updateNote(existingNote);

        return "Note updated successfully.";
    }

    @DeleteMapping("/{id}")
    public String deleteNoteById(@PathVariable Long id) {
        myNoteService.deleteNote(id);
        return "Note deleted successfully.";
    }
}
