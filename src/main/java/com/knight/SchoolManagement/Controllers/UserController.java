package com.knight.SchoolManagement.Controllers;

import com.knight.SchoolManagement.Services.FrequencyService;
import com.knight.SchoolManagement.Services.UserService;
import com.knight.SchoolManagement.entities.Discipline;
import com.knight.SchoolManagement.entities.Frequency;
import com.knight.SchoolManagement.entities.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FrequencyService frequencyService;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<User> newUser(@RequestBody User obj){
        return ResponseEntity.ok().body(userService.newUser(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserData(@PathVariable UUID id, @RequestBody User obj){
        return ResponseEntity.ok().body(userService.updateUserData(id, obj).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){

        userService.removeUser(id);

        return ResponseEntity.noContent().build();
    }

    //This endpoint is for return the disciplines that the user is registered,
    // and also his frequency.
    @GetMapping("/{id}/frequencies")
    public ResponseEntity<List<Frequency>> findAllDisciplines (@PathVariable UUID id){

        List<Frequency> studentFrequencies = frequencyService
                .findUserFrequencies(userService.findById(id));

        return ResponseEntity.ok().body(studentFrequencies);
    }
}

