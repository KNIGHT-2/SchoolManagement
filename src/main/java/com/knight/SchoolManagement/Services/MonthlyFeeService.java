package com.knight.SchoolManagement.Services;

import com.knight.SchoolManagement.Repository.MonthlyFeeRepository;
import com.knight.SchoolManagement.entities.MonthlyFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonthlyFeeService {

    @Autowired
    MonthlyFeeRepository repository;

    public MonthlyFee saveFee(MonthlyFee fee){
        return repository.save(fee);
    }

}
