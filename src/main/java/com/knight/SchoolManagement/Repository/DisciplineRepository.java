package com.knight.SchoolManagement.Repository;

import com.knight.SchoolManagement.entities.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DisciplineRepository extends JpaRepository<Discipline, UUID> {
    Optional<Discipline> findByName(String name);
}
