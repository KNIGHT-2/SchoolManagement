package com.knight.SchoolManagement.Repository;

import com.knight.SchoolManagement.entities.Discipline;
import com.knight.SchoolManagement.entities.Frequency;
import com.knight.SchoolManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FrequencyRepository extends JpaRepository<Frequency, UUID> {
    List<Optional<Frequency>> findByDiscipline(Discipline discipline);
}
