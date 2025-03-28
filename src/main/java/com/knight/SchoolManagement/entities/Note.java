package com.knight.SchoolManagement.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private AssessmentType assessmentType;
    private Double note;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "frequency_id")
    private Frequency frequency;

    private enum AssessmentType{
        AV1,
        AV2,
        AV3
    }

    public Note(){}

    public Note(User student, Double note, Discipline discipline, String assessmentType) {
        this.student = student;
        this.note = note;
        this.discipline = discipline;
        this.assessmentType = AssessmentType.valueOf(assessmentType);
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public UUID getId() {
        return id;
    }

    public String getAssessmentType() {
        return String.valueOf(assessmentType);
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = AssessmentType.valueOf(assessmentType);
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
