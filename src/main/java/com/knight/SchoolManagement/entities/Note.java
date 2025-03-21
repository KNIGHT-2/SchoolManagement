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


    private enum AssessmentType{
        AV1,
        AV2,
        AV3
    }

    public Note(){}

    public Note(Double note, Discipline discipline, String assessmentType) {
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
