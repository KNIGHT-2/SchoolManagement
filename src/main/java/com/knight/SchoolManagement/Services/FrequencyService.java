package com.knight.SchoolManagement.Services;

import com.knight.SchoolManagement.Repository.DisciplineRepository;
import com.knight.SchoolManagement.Repository.FrequencyRepository;
import com.knight.SchoolManagement.entities.DTO.NoteDTO;
import com.knight.SchoolManagement.entities.Discipline;
import com.knight.SchoolManagement.entities.Frequency;
import com.knight.SchoolManagement.entities.Note;
import com.knight.SchoolManagement.entities.User;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class FrequencyService {

    @Autowired
    FrequencyRepository frequencyRepository;

    @Autowired
    UserService userService;

    @Autowired
    DisciplineRepository disciplineRepository;

    @Autowired
    DisciplineService disciplineService;

    @Autowired
    NoteService noteService;

    public List<Frequency> findAll(){
        return frequencyRepository.findAll();
    }

    //This method is to filter the frequencies that belong to the specified discipline.
    public List<Frequency> findAllByDisciplineName(String name){

        if(name == null){
            throw new InputMismatchException("Null is not a valid input.");
        }

        return findAll().stream().filter(s -> s.getDisciplineName().equals(name)).toList();
    }

    //This method is to filter the frequencies that belong to the user.
    public List<Frequency> findUserFrequencies(User user){
        return findAll().stream().filter(f -> f.getUser().getId().equals(user.getId())).toList();
    }

    //This method adds a new note to the specified User.
    public Frequency addNoteToUser(NoteDTO noteDTO, UUID userId){

        Discipline discipline = disciplineService.findByName(noteDTO.name());
        User student = userService.findById(userId);

        Note note = new Note(student, noteDTO.note(), discipline, noteDTO.assessmentType());

        noteService.saveNote(note);

        List<Optional<Frequency>> frequenciesByDiscipline = frequencyRepository.findByDiscipline(discipline);

        Frequency frequency = frequenciesByDiscipline.stream()
                .filter(f -> f.get().getUser().getId().equals(userId)).toList().getFirst()
                .orElseThrow(() -> new EntityNotFoundException("Frequency does not exist."));

        List<Note> notes = frequency.getDiscipline().getNotes();
        notes.add(note);
        frequency.getDiscipline().setNotes(notes);

        return frequencyRepository.save(frequency);
    }

    @Transactional
    public Frequency insertFrequencyToUser(UUID userId, String disciplineName){
        Discipline discipline = disciplineService.findByName(disciplineName);

        User user = userService.findById(userId);

        Frequency frequency = new Frequency(null, user, discipline);

        discipline.getFrequencies().add(frequency);

        frequencyRepository.save(frequency);
        disciplineRepository.save(discipline);
        return frequency;
    }
}
