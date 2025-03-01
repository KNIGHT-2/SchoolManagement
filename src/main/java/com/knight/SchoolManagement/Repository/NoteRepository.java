package com.knight.SchoolManagement.Repository;

import com.knight.SchoolManagement.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {
}
