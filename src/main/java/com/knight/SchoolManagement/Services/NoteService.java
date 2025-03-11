package com.knight.SchoolManagement.Services;

import com.knight.SchoolManagement.Repository.NoteRepository;
import com.knight.SchoolManagement.entities.Discipline;
import com.knight.SchoolManagement.entities.Note;
import com.knight.SchoolManagement.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.UUID;

@Component
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    DisciplineService disciplineService;

    @Autowired
    UserService userService;

    public Note saveNote(Note note){
        return noteRepository.save(note);
    }
}
