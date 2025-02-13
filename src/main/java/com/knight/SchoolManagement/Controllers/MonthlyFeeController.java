package com.knight.SchoolManagement.Controllers;

import com.knight.SchoolManagement.Services.MonthlyFeeService;
import com.knight.SchoolManagement.entities.MonthlyFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fee")
public class MonthlyFeeController {

    @Autowired
    MonthlyFeeService monthlyFeeService;

    //This endpoint is for add a fee to a specific user.
    @PostMapping
    @RequestMapping("/{userId}")
    public ResponseEntity<MonthlyFee> addFeeTo(@PathVariable UUID userId, @RequestBody MonthlyFee fee){
        return ResponseEntity.ok().body(monthlyFeeService.saveFee(userId, fee));
    }

    //This endpoint returns the value that the user have to pay, including late fee.
    @GetMapping
    @RequestMapping("/{userId}/values")
    public ResponseEntity<List<Double>> valuesToPay(@PathVariable UUID userId){
        return ResponseEntity.ok().body(monthlyFeeService.valuesToPay(userId));
    }
    //This endpoint calls the method that adds a fee to all students and returns how many users had a monthly fee added.
    @PostMapping
    @RequestMapping("/toAllStudents")
    public ResponseEntity<String> addFeeToAllStudents(@RequestBody MonthlyFee monthlyFee){
        return ResponseEntity.ok().body("Monthly fee added to " + monthlyFeeService
                .addFeeToAllStudents(monthlyFee).toString() + " users.");
    }

}
