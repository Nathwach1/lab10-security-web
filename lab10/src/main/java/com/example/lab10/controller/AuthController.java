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
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;


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
            BindingResult bindingResult,
            HttpServletRequest httpRequest
    ) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            registerService.register(request);

            // 🔐 AUTO-LOGIN après register
            httpRequest.login(request.getEmail(), request.getPassword());

        } catch (IllegalStateException e) {
            bindingResult.rejectValue("email", "error.email", e.getMessage());
            return "register";
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/notes";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
