package com.theodore.mandarinsrs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.theodore.mandarinsrs.service.UserService;
import com.theodore.mandarinsrs.service.UsernameAlreadyExistsException;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

  private final UserService userService;

  public MainController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/")
  public String home(Model model, HttpSession session) {
    String name = (String) session.getAttribute("name");
    model.addAttribute("name", name);
    model.addAttribute("title", "Home page");
    return "homepage";
  }


  @GetMapping("/login")
  public String login(Model model) {
    model.addAttribute("title", "Log in page");
    return "login";
  }

  @GetMapping("/register")
  public String register(Model model) {
    model.addAttribute("title", "Register page");
    return "register";
  }

  @PostMapping("/register")
  public String submit(@RequestParam String username,
                     @RequestParam String password,
                     @RequestParam String passwordConfirm,
                     Model model) {

    /*if (username.isBlank() || username.length() < 3) {
        model.addAttribute("error", "Username must be at least 3 characters");
        model.addAttribute("username", username);
        return "register";
    }
    if (password == null || password.length() < 8) {
        model.addAttribute("error", "Password must be at least 8 characters");
        model.addAttribute("username", username);
        return "register";
    }*/
    if (!password.equals(passwordConfirm)) {
        model.addAttribute("error", "Passwords do not match");
        model.addAttribute("username", username);
        return "register";
    }

    try {
        userService.register(username, password);
    } catch (UsernameAlreadyExistsException e) {
        model.addAttribute("error", "That username is already taken");
        model.addAttribute("username", username);
        return "register";
    }

    return "redirect:/login?registered";
  }

  @GetMapping("/review")
  public String review(Model model) {
    model.addAttribute("title", "Review page");
    return "review";
  }
}