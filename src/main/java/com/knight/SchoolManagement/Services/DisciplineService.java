package com.knight.SchoolManagement.Services;

import com.knight.SchoolManagement.Repository.DisciplineRepository;
import com.knight.SchoolManagement.entities.Discipline;
import com.knight.SchoolManagement.entities.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Component
public class DisciplineService {

    @Autowired
    DisciplineRepository disciplineRepository;

    public List<Discipline> findAll(){
        return disciplineRepository.findAll();
    }

    public Discipline findByName(String disciplineName){
        return disciplineRepository.findByName(disciplineName).orElseThrow(InputMismatchException::new);
    }
}
