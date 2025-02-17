package com.knight.SchoolManagement.Services;

import com.knight.SchoolManagement.Repository.FrequencyRepository;
import com.knight.SchoolManagement.entities.Frequency;
import com.knight.SchoolManagement.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;

@Component
public class FrequencyService {

    @Autowired
    FrequencyRepository frequencyRepository;

    public List<Frequency> findAll(){
        return frequencyRepository.findAll();
    }

    //This method is to filter the frequencies that belong to the specified discipline.
    public List<Frequency> findAllByDisciplineName(String name){

        if(name == null){
            throw new InputMismatchException("Null is not a valid input.");
        }

        return findAll().stream().filter(s -> s.getDisciplineName().equals(name)).toList();
    }

    //This method is to filter the frequencies that belong to the user.
    public List<Frequency> findUserFrequencies(User user){
        return findAll().stream().filter(f -> f.getUser().getId().equals(user.getId())).toList();
    }
}
