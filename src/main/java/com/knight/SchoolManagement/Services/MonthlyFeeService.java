package com.knight.SchoolManagement.Services;

import com.knight.SchoolManagement.Repository.MonthlyFeeRepository;
import com.knight.SchoolManagement.entities.MonthlyFee;
import com.knight.SchoolManagement.entities.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MonthlyFeeService {

    @Autowired
    MonthlyFeeRepository repository;

    @Autowired
    UserService userService;

    public MonthlyFee saveFee(UUID id, MonthlyFee fee){

        User student = userService.findById(id);

        fee.setUser(student);

        return repository.save(fee);
    }

    //This method returns the values that a specific user have to pay (monthly fee value + late fee value).
    public List<Double> valuesToPay(UUID userId){
        List<MonthlyFee> fee = repository.findAll().stream().filter(f -> f.getUser().getId().equals(userId)).toList();
        List<Double> values = new ArrayList<>();
        fee.forEach(f -> values.add(f.valueToPay()));
        return values;
    }

    //This method adds a fee to all students
    public Integer addFeeToAllStudents(MonthlyFee monthlyFee){
        List<User> students = userService.findAll().stream()
                .filter(user -> user.getRole().equals(User.Role.STUDENT)).toList();

        students.forEach(s -> {
            MonthlyFee newFee = new MonthlyFee(monthlyFee.getValue());
            saveFee(s.getId(), newFee);});

        return students.stream().map(s -> s.getId()).toList().size();
    }

}
