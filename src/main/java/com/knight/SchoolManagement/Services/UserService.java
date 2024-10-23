package com.knight.SchoolManagement.Services;

import com.knight.SchoolManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.knight.SchoolManagement.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(UUID id){
        return userRepository.findById(id);
    }

    public User newUser(User newUser){
        return userRepository.save(newUser);
    }
}
