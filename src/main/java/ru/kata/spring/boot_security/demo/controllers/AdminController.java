package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String listUsers(Model model, Principal principal) {
        model.addAttribute("users", userService.getUsers());
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("userOne", user);
        model.addAttribute("allRoles", userService.findRoles());
        return "admin";
    }

    @GetMapping("/admin/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.showUserById(id));
        return "admin";
    }

    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("newUser") User user) {
        return "/admin";
    }

    @PostMapping
    public String addUser(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.showUserById(id));
        model.addAttribute("allRoles", userService.findRoles());
        return "admin";
    }

    @PatchMapping("/admin/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUserById(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

}

