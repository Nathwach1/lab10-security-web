package com.example.lab10.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import com.example.lab10.dto.RegisterRequest;
import com.example.lab10.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AuthController {

    private final RegisterService registerService;

    public AuthController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }


    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute RegisterRequest request,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        registerService.register(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "User successfully created. You can now log in."
        );

        return "redirect:/login";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }



    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

