package com.knight.SchoolManagement;

import com.knight.SchoolManagement.Repository.UserRepository;
import com.knight.SchoolManagement.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User user = new User(null, "Bob", LocalDate.of(2024, 07, 15), "TEACHER");
        User user1 = new User(null, "John", LocalDate.of(2023, 02, 28), "STUDENT");
        User user2 = new User(null, "Ana", LocalDate.of(2022, 05, 10), "STUDENT");

        userRepository.saveAll(Arrays.asList(user, user1, user2));
    }
}