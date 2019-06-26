package ru.itpark.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itpark.finalproject.service.CardSerrvice;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class CardController {
  private final CardSerrvice serrvice;


}
