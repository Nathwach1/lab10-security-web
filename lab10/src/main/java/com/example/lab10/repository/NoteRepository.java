package com.example.lab10.repository;

import com.example.lab10.model.Note;
import com.example.lab10.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findByUser(User user);
}
