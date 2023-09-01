package com.yaqubmw.mynote.service;

import com.yaqubmw.mynote.model.Note;
import com.yaqubmw.mynote.repository.MyNoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyNoteService {
    
    private final MyNoteRepository myNoteRepository;

    public MyNoteService(MyNoteRepository myNoteRepository) {
        this.myNoteRepository = myNoteRepository;
    }

    public void createNote(Note note) {
        myNoteRepository.createNote(note);
    }

    public List<Note> getAllNotes() {
        return myNoteRepository.getAllNotes();
    }

    public Note getNoteById(Long id) {
        return myNoteRepository.getNoteById(id);
    }

    public void updateNote(Note note) {
        myNoteRepository.updateNote(note);
    }

    public void deleteNote(Long id) {
        myNoteRepository.deleteNote(id);
    }
}
