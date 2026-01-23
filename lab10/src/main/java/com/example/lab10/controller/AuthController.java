package com.example.lab10.controller;

import com.example.lab10.dto.RegisterRequest;
import com.example.lab10.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AuthController {

    private final RegisterService registerService;

    public AuthController(RegisterService registerService) {
        this.registerService = registerService;
    }

    // 1️⃣ SHOW REGISTER PAGE
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    // 2️⃣ HANDLE REGISTER SUBMISSION
    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("registerRequest") RegisterRequest request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            registerService.register(request);
        } catch (IllegalStateException e) {
            bindingResult.rejectValue("email", "error.email", e.getMessage());
            return "register";
        }

        return "redirect:/welcome";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

}
