package ru.itpark.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itpark.finalproject.dto.CardAdd;
import ru.itpark.finalproject.service.CardService;

@Controller
@RequestMapping("/add-card")
@RequiredArgsConstructor
public class CardController {
  private final CardService service;

  @GetMapping
  public String addCard(){
    return "admin/add-card";
  }

  @PostMapping("@(/asdasd)")
  public String addCard(@ModelAttribute CardAdd dto){
    service.add(dto);
    return "redirect:/";
  }
}
