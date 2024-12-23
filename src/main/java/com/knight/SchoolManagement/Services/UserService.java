package com.knight.SchoolManagement.Services;

import com.knight.SchoolManagement.Repository.MonthlyFeeRepository;
import com.knight.SchoolManagement.Repository.UserRepository;
import com.knight.SchoolManagement.entities.MonthlyFee;
import org.springframework.beans.factory.annotation.Autowired;
import com.knight.SchoolManagement.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(UUID id){
        return userRepository.findById(id);
    }

    public User newUser(User newUser){
        MonthlyFee firstFee = new MonthlyFee(200.0, LocalDate.now(), newUser);
        User obj = userRepository.save(newUser);
        //If the new user is a student, the first monthly fee will be automatically generated.
        if(newUser.getRole().equals(User.Role.STUDENT)) {
            addFee(newUser, firstFee);
        }

        return obj;
    }

    //This method is for update the user's name
    public Optional<User> updateUserData(UUID id, User obj){
        Optional<User> user = userRepository.findById(id);
        user.get().setName(obj.getName());
        userRepository.save(user.get());
        return user;
    }

    //This method is for the creation of a new fee
    public void addFee(User user, MonthlyFee fee){
        user.getStudentFees().add(fee);
        monthlyFeeRepository.save(fee);
        System.out.println("Fee added to user: " + user.getId());
    }
}
