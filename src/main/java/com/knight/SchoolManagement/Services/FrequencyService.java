package com.knight.SchoolManagement.Services;

import com.knight.SchoolManagement.Repository.FrequencyRepository;
import com.knight.SchoolManagement.entities.Frequency;
import com.knight.SchoolManagement.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FrequencyService {

    @Autowired
    FrequencyRepository frequencyRepository;

    public List<Frequency> findAll(){
        return frequencyRepository.findAll();
    }

    //This method is to filter the frequencies that belong to the user
    public List<Frequency> findUserFrequencies(User user){
        List<Frequency> userFrequencies = findAll().stream()
                .filter(f -> f.getUser().getId().equals(user.getId())).toList();
        return userFrequencies;
    }
}
