package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.Role;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping
    public String processRegister(@RequestParam String email,
                                  @RequestParam String username,
                                  @RequestParam String password,
                                  @RequestParam String confirmPassword,
                                  Model model) {
        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            model.addAttribute("errorMessage", "Passwords do not match!");
            return "register";
        }

        // Check if the username already exists
        if (userService.userExists(username)) {
            model.addAttribute("errorMessage", "Username already exists!");
            return "register";
        }

        try {
            // Create and save the user
            User newUser = new User();
            newUser.setEmail(email); // Save the email
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password)); // Encrypt the password
            newUser.getRoles().add(Role.ROLE_USER); // Default role assignment
            userService.save(newUser);

            model.addAttribute("successMessage", "Account created successfully! Please log in.");
            return "redirect:/login";

        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            model.addAttribute("errorMessage", "An unexpected error occurred. Please try again.");
            return "register";
        }
    }

}
