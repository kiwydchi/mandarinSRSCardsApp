package com.theodore.javingthontest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.theodore.javingthontest.service.UserService;

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

  @PostMapping("/login")
  public String login(@RequestParam String name, @RequestParam String password, Model model, HttpSession session) {
    if (userService.login(name, password)) {
      session.setAttribute("name", name);
      session.setAttribute("password", password);
      return "redirect:/";
    }
    model.addAttribute("error", "Invalid credentials");
    return "login";
  }
}