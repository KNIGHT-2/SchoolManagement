package com.knight.SchoolManagement.Services;

import com.knight.SchoolManagement.Repository.DisciplineRepository;
import com.knight.SchoolManagement.entities.Discipline;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DisciplineService {

    @Autowired
    DisciplineRepository disciplineRepository;

    public List<Discipline> findAll(){
        return disciplineRepository.findAll();
    }

}
