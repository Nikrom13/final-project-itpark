package ru.itpark.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itpark.finalproject.dto.CardAdd;

@Controller
@RequestMapping("/admin")
public class AdminController {
  @GetMapping
  public String index() {
    return "admin/index";
  }



//  @PostMapping("/add")
//  public String addCard(@ModelAttribute CardAdd dto){
//
//  }
}
