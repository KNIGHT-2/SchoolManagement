package com.knight.SchoolManagement;

import com.knight.SchoolManagement.Repository.DisciplineRepository;
import com.knight.SchoolManagement.Repository.FrequencyRepository;
import com.knight.SchoolManagement.Repository.MonthlyFeeRepository;
import com.knight.SchoolManagement.Repository.UserRepository;
import com.knight.SchoolManagement.entities.Discipline;
import com.knight.SchoolManagement.entities.Frequency;
import com.knight.SchoolManagement.entities.MonthlyFee;
import com.knight.SchoolManagement.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MonthlyFeeRepository monthlyFeeRepository;

    @Autowired
    DisciplineRepository disciplineRepository;

    @Autowired
    FrequencyRepository frequencyRepository;

    @Override
    public void run(String... args) throws Exception {
        disciplineRepository.deleteAll();
        frequencyRepository.deleteAll();
        monthlyFeeRepository.deleteAll();
        userRepository.deleteAll();


        User user = new User(null, "Bob", LocalDate.of(2024, 07, 15), "TEACHER");
        User user1 = new User(null, "John", LocalDate.of(2023, 02, 28), "STUDENT");
        User user2 = new User(null, "Ana", LocalDate.of(2022, 05, 10), "STUDENT");

        userRepository.saveAll(Arrays.asList(user, user1, user2));

        MonthlyFee fee = new MonthlyFee(200.0, LocalDate.now(), user1);
        monthlyFeeRepository.save(fee);

        List<Frequency> mathFrequencies = new ArrayList<>();
        List<Frequency> englishFrequencies = new ArrayList<>();

        mathFrequencies.addAll(Arrays.asList(new Frequency(null, 10, user),
                new Frequency(null, 3, user1)));

        englishFrequencies.addAll(Arrays.asList(new Frequency(null, 4, user2), new Frequency(null, 6, user1)));

        Discipline discipline1 = new Discipline(null, "Math", mathFrequencies);
        Discipline discipline2 = new Discipline(null, "English", englishFrequencies);

        disciplineRepository.saveAll(Arrays.asList(discipline1, discipline2));

        mathFrequencies.forEach(frequency -> frequency.setDiscipline(discipline1));
        englishFrequencies.forEach(frequency -> frequency.setDiscipline(discipline2));

        frequencyRepository.saveAll(mathFrequencies);
        frequencyRepository.saveAll(englishFrequencies);


    }
}
