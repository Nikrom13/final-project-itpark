package ru.itpark.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itpark.finalproject.dto.CardAdd;
import ru.itpark.finalproject.service.CardService;

@Controller
@RequiredArgsConstructor
public class AdminController {
  public final CardService service;

  @GetMapping
  @RequestMapping("/admin")
  public String index() {
    return "admin/index";
  }

  @GetMapping("/add-card")
  public String addCard(){
    return "card/add-card";
  }

  @PostMapping("/add-card")
  public String addCard(@ModelAttribute CardAdd dto){
    service.add(dto);
    return "redirect:/";
  }

  @PostMapping("/remove/{id}")
  public String removeById(@PathVariable int id){
    service.removeById(id);
    return "redirect:/";
  }



}
