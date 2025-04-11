package com.knight.SchoolManagement;

import com.knight.SchoolManagement.Repository.*;
import com.knight.SchoolManagement.Services.FrequencyService;
import com.knight.SchoolManagement.Services.UserService;
import com.knight.SchoolManagement.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    FrequencyService frequencyService;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {

        disciplineRepository.deleteAll();
        frequencyRepository.deleteAll();
        monthlyFeeRepository.deleteAll();
        noteRepository.deleteAll();
        userRepository.deleteAll();

        Optional<User> userAdmin = userRepository.findByName("Admin");

        if(userAdmin.isPresent()){
            System.out.println("Admin already exists.");
        }else{
            User user = new User(null, "admin@admin.com", "Admin", bCryptPasswordEncoder.encode("1234"), LocalDate.now(), "ADMIN");
            userRepository.save(user);
        }



        User user = new User(null,"bob@email.com", "Bob", "1234", LocalDate.of(2024, 7, 15), "TEACHER");
        User user1 = new User(null,"John@email.com", "John", "1234", LocalDate.of(2023, 2, 28), "STUDENT");
        User user2 = new User(null,"ana@email.com", "Ana", "1234", LocalDate.of(2022, 5, 10), "STUDENT");
        User user3 = new User(null,"pietro@email.com", "Pietro", "1234", LocalDate.of(2023, 6, 19), "STUDENT");

        userRepository.saveAll(Arrays.asList(user, user1, user2, user3));

        MonthlyFee fee = new MonthlyFee(200.0, LocalDate.now(), user2);
        MonthlyFee fee1 = new MonthlyFee(200.0, LocalDate.of(2025, 1, 20), user1);

        monthlyFeeRepository.saveAll(Arrays.asList(fee, fee1));

        List<Frequency> mathFrequencies = new ArrayList<>();
        List<Frequency> englishFrequencies = new ArrayList<>();

        mathFrequencies.addAll(Arrays.asList(new Frequency(null, 10, user),
                new Frequency(null, 3, user1),
                new Frequency(null, 13, user3)));

        englishFrequencies.addAll(Arrays.asList(new Frequency(null, 4, user2),
                new Frequency(null, 6, user1),
                new Frequency(null, 4, user3)));

        Discipline discipline1 = new Discipline(null, "Math", mathFrequencies);
        Discipline discipline2 = new Discipline(null, "English", englishFrequencies);

        disciplineRepository.saveAll(Arrays.asList(discipline1, discipline2));

        mathFrequencies.forEach(frequency -> frequency.setDiscipline(discipline1));
        englishFrequencies.forEach(frequency -> frequency.setDiscipline(discipline2));

        frequencyRepository.saveAll(mathFrequencies);
        frequencyRepository.saveAll(englishFrequencies);

        Note note = new Note(user1, 8.2, discipline1, "AV1");
        Note note1 = new Note(user2, 7.9, discipline2, "AV1");
        noteRepository.saveAll(Arrays.asList(note, note1));

        User testUser = userRepository.findByName("Admin").get();
        frequencyService.insertFrequencyToUser(testUser.getId(), "Math");

        List<Frequency> userFrequencies = frequencyService.findUserFrequencies(testUser);
        userFrequencies.forEach(s -> System.out.println(s.getDisciplineName()));

        System.out.println("-----------------");
        List<Frequency> frequencyLit = frequencyService.findAll();
        frequencyLit.forEach(s -> System.out.println(s.getDisciplineName()));

        var disciplineList = disciplineRepository.findAll();
        for(int i = 0; i < disciplineList.size(); i++){
            for(int j = 0; j < disciplineList.get(i).getNotes().size(); j++){
                System.out.println(disciplineList.get(i).getNotes().get(j));
            }
        }
    }
}
