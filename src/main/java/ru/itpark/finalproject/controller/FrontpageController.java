package ru.itpark.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FrontpageController {

  @GetMapping
  public String frontPage() {
    return "index";
  }
}
