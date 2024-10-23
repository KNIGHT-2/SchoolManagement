package com.knight.SchoolManagement.Controllers;

import com.knight.SchoolManagement.Services.UserService;
import com.knight.SchoolManagement.entities.User;
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

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id){
        return ResponseEntity.ok(userService.findById(id).orElseThrow(InputMismatchException::new));
    }

    @PostMapping("/nu")
    public ResponseEntity<User> newUser(@RequestBody User obj){
        return ResponseEntity.ok().body(userService.newUser(obj));
    }

}
