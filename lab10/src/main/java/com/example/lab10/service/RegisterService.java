package com.example.lab10.service;

import com.example.lab10.dto.RegisterRequest;
import com.example.lab10.model.User;
import com.example.lab10.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String username, String email, String password) {

        if (userRepository.existsByEmail(email)) {
            throw new IllegalStateException("Email already registered");
        }

        String role;
        if (email.endsWith("@work.com")) {
            role = "ROLE_ADMIN";
        } else {
            role = "ROLE_USER";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        userRepository.save(user);
    }
}
