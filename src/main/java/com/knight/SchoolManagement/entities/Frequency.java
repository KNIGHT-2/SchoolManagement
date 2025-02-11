package com.knight.SchoolManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Frequency{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Integer faults;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    public Frequency(){}

    public Frequency(UUID id, Integer faults, User student) {
        this.id = id;
        this.faults = faults;
        this.user = student;
    }

    public UUID getId() {
        return id;
    }

    public Integer getFaults() {
        return faults;
    }

    public void setFaults(Integer faults) {
        this.faults = faults;
    }

    public User getUser() {
        return user;
    }

    @JsonIgnore
    public Discipline getDiscipline() {
        return discipline;
    }

    public UUID getDisciplineId(){
        return discipline.getId();
    }
    public String getDisciplineName(){
        return discipline.getName();
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frequency frequency = (Frequency) o;
        return Objects.equals(id, frequency.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
