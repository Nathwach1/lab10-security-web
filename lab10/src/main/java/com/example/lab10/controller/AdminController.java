package com.example.lab10.controller;

import com.example.lab10.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("users", adminService.getAllUsers());
        return "admin";
    }
}
