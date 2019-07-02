package ru.itpark.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itpark.finalproject.domain.Registration;
import ru.itpark.finalproject.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
  private final UserService service;

  @GetMapping
  public String form() {
    return "register";
  }

  @PostMapping
  public String register(@ModelAttribute Registration data, Model model) {
    try {
      service.register(data);
    } catch (RuntimeException e) {
      // TODO: handle exception
      model.addAttribute("error", e.getMessage());
      return "register";
    }

    return "redirect:/login?register"; // Чтобы можно было понять, что пришли с регистрации
  }
}
