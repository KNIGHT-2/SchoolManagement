package com.knight.SchoolManagement.Repository;

import com.knight.SchoolManagement.entities.MonthlyFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MonthlyFeeRepository extends JpaRepository<MonthlyFee, UUID> {
}
