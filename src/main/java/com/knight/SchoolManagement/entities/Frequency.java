package com.knight.SchoolManagement.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.knight.SchoolManagement.entities.DTO.UserDTO;
import jakarta.persistence.*;

import java.util.List;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    /*@OneToMany(mappedBy = "frequency", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;
*/
    public Frequency(){}

    @JsonCreator
    public Frequency(User user){
        this.user = user;
        faults = 0;
    }

    public Frequency(UUID id, User student, Discipline discipline) {
        this.id = id;
        faults = 0;
        this.user = student;
        this.discipline = discipline;
    }

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

    //This method returns a DTO from the user associated in this frequency.
    public UserDTO getUser() {
        return user.toDTO(user);
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

    public List<Note> getStudentNotes(){
        List<Note> notes = getDiscipline().getNotes().stream().filter(n -> n.getStudent().equals(this.user)).toList();
        return notes;
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
