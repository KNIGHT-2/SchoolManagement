package com.knight.SchoolManagement.Services;

import com.knight.SchoolManagement.Repository.MonthlyFeeRepository;
import com.knight.SchoolManagement.Repository.UserRepository;
import com.knight.SchoolManagement.entities.MonthlyFee;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.knight.SchoolManagement.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MonthlyFeeRepository monthlyFeeRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(UUID id){

        if (!existsById(id)){
            throw new EntityNotFoundException();
        }

        return userRepository.findById(id).get();
    }

    public Optional<User> findByName(String name){
        return userRepository.findByName(name);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User newUser(User newUser){

        if(userRepository.findByEmail(newUser.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        User obj = userRepository.save(newUser);
        //If the new user is a student, the first monthly fee will be automatically generated.
        if(newUser.getRole().equals(User.Role.STUDENT)) {
            MonthlyFee firstFee = new MonthlyFee(200.0, LocalDate.now(), newUser);
            addFee(newUser, firstFee);
        }

        return obj;
    }

    //This method is used to check if there is a user with the parameter Id.
    public boolean existsById(UUID id){
        return userRepository.existsById(id);
    }

    //This method is for update the user's name
    public Optional<User> updateUserData(UUID id, User obj){

        if (!existsById(id)){
            throw new EntityNotFoundException();
        }

        Optional<User> user = userRepository.findById(id);
        user.get().setName(obj.getName());
        userRepository.save(user.get());
        return user;
    }

    public void removeUser(UUID id){

        if (!existsById(id)){
            throw new EntityNotFoundException();
        }

        userRepository.deleteById(id);
        System.out.println("User " + id + " deleted with success.");
    }

    //This method is for the creation of a new fee
    public void addFee(User user, MonthlyFee fee){
        user.getStudentFees().add(fee);
        monthlyFeeRepository.save(fee);
        System.out.println("Fee added to user: " + user.getId());
    }
}
