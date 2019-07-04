package ru.itpark.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.finalproject.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
  private final UserService service;

  @GetMapping
  public String form() {
    return "login";
  }

  @PostMapping
  public String login(@RequestParam String username, Model model) {
    try {
      service.loadUserByUsername(username);
      model.addAttribute("username", username);
    } catch (UsernameNotFoundException e) {
      model.addAttribute("error", e.getMessage());
      return "login";
    }
    return "redorect:/";
  }
}
