package com.knight.SchoolManagement.Controllers;

import com.knight.SchoolManagement.Services.FrequencyService;
import com.knight.SchoolManagement.entities.DTO.NoteDTO;
import com.knight.SchoolManagement.entities.Frequency;
import com.knight.SchoolManagement.entities.Note;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/frequencies")
public class FrequencyController {

    @Autowired
    FrequencyService frequencyService;

    @GetMapping
    public ResponseEntity<List<Frequency>> findALlFrequencies(){
        return ResponseEntity.ok().body(frequencyService.findAll());
    }

    //This endpoint is to find all frequencies of a specific discipline.
    @GetMapping
    @RequestMapping(value = "/search")
    public ResponseEntity<List<Frequency>> findFrequenciesByDiscipline(@RequestParam String discipline){
        return ResponseEntity.ok().body(frequencyService.findAllByDisciplineName(discipline));
    }

    @PutMapping
    @RequestMapping(value = "/saveNote/{userId}")
    public ResponseEntity<Frequency> saveNoteToUser(@PathVariable UUID userId, @RequestBody NoteDTO noteDTO){
        return ResponseEntity.ok().body(frequencyService.addNoteToUser(noteDTO, userId));
    }
}
