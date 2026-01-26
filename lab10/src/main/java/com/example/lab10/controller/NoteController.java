package com.example.lab10.controller;

import com.example.lab10.model.Note;
import com.example.lab10.model.User;
import com.example.lab10.repository.NoteRepository;
import com.example.lab10.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteController(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    // 📄 PAGE DES NOTES (GET)
    @GetMapping
    public String notes(Authentication authentication, Model model) {

        String email = authentication.getName(); // username = email
        User user = userRepository.findByEmail(email)
                .orElseThrow();

        model.addAttribute("notes", noteRepository.findByUser(user));
        model.addAttribute("note", new Note());

        return "notes";
    }

    // ➕ CRÉER UNE NOTE (POST)
    @PostMapping
    public String createNote(
            @ModelAttribute Note note,
            Authentication authentication
    ) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow();

        note.setUser(user);
        noteRepository.save(note);

        return "redirect:/notes";
    }
}
