package com.knight.SchoolManagement.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.knight.SchoolManagement.entities.DTO.UserDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
public class User {

    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;
    private LocalDate registration;
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MonthlyFee> studentFees = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Frequency> frequencies;

    public User(){}

    //Just for tests
    public User(UUID id, String name, LocalDate registration, String role) {
        this.id = id;
        this.name = name;
        this.registration = registration;
        this.role = Role.valueOf(role);
    }

    //For add users with the current date
    @JsonCreator
    public User(UUID id, String name, String role) {
        this.id = id;
        this.name = name;
        this.registration = LocalDate.now();
        this.role = Role.valueOf(role);
    }

    public UserDTO toDTO(User user){
        return new UserDTO(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDate registration) {
        this.registration = registration;
    }

    public Role getRole() {
        return role;
    }

    public List<MonthlyFee> getStudentFees() {
        return studentFees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public enum Role {
        TEACHER,
        STUDENT;
    }
}
