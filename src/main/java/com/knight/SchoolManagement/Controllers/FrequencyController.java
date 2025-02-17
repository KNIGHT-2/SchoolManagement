package com.knight.SchoolManagement.Controllers;

import com.knight.SchoolManagement.Services.FrequencyService;
import com.knight.SchoolManagement.entities.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/frequencies")
public class FrequencyController {

    @Autowired
    FrequencyService frequencyService;

    @GetMapping
    public ResponseEntity<List<Frequency>> findALlFrequencies(){
        return ResponseEntity.ok().body(frequencyService.findAll());
    }

}
