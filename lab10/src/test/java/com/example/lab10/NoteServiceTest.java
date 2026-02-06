package com.example.lab10;

import com.example.lab10.model.Note;
import com.example.lab10.model.User;
import com.example.lab10.repository.NoteRepository;
import com.example.lab10.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
@ActiveProfiles("test")
@DataJpaTest
class NoteServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Test
    void notesAreLinkedToUser() {
        User user = new User();
        user.setUsername("testuser");          // ✅ AJOUT ICI
        user.setEmail("user@test.com");
        user.setPassword("hashed");
        user.setRole("ROLE_USER");

        user = userRepository.save(user);

        Note note = new Note();
        note.setTitle("Test note");
        note.setContent("Content");
        note.setUser(user);

        noteRepository.save(note);

        assertThat(noteRepository.findAll()).hasSize(1);
        assertThat(noteRepository.findAll().get(0).getUser().getUsername())
                .isEqualTo("testuser");
    }
}