package ru.itpark.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itpark.finalproject.service.CardService;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class FrontpageController {
  private final CardService service;

  @GetMapping
  public String frontPage(Model model) {
    model.addAttribute("cards", service.findAll());
    return "index";
  }






}
