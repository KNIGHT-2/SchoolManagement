package com.knight.SchoolManagement.Services;

import com.knight.SchoolManagement.Repository.MonthlyFeeRepository;
import com.knight.SchoolManagement.entities.MonthlyFee;
import com.knight.SchoolManagement.entities.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.UUID;

@Service
public class MonthlyFeeService {

    @Autowired
    MonthlyFeeRepository repository;

    @Autowired
    UserService userService;

    public MonthlyFee saveFee(UUID id, MonthlyFee fee){

        User student = userService.findById(id).orElseThrow(EntityNotFoundException::new);

        fee.setUser(student);

        return repository.save(fee);
    }

    public List<Double> valuesToPay(UUID userId){
        List<MonthlyFee> fee = repository.findAll().stream().filter(f -> f.getUser().getId().equals(userId)).toList();
        List<Double> values = new ArrayList<>();
        fee.forEach(f -> values.add(f.valueToPay()));
        return values;
    }

}
