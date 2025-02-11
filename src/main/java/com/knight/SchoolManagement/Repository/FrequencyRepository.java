package com.knight.SchoolManagement.Repository;

import com.knight.SchoolManagement.entities.Frequency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FrequencyRepository extends JpaRepository<Frequency, UUID> {
}
