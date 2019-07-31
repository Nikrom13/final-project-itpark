package ru.itpark.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.finalproject.domain.Card;
import ru.itpark.finalproject.dto.CardAdd;
import ru.itpark.finalproject.service.CardService;
import ru.itpark.finalproject.service.UserService;

import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    public final CardService service;
    public final UserService userService;

    @GetMapping
    @RequestMapping("/admin")
    public String index(Model model) {
        model.addAttribute("cards",service.findAll());
        return "admin/index";
    }


    @GetMapping(params = "search")
    @RequestMapping("/cardSearch")
    public String index(@RequestParam String search, Model model) {
        model.addAttribute("cardSearch", search);
        model.addAttribute( "cards", service.findByRequest(search));
       return "admin/index";

    }


    @GetMapping("/sort")
    public String sort(Model model) {
        model.addAttribute("cards", service.sortByRate());
        return "admin/index";
    }

    @GetMapping("/user-list")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "admin/user-list";
    }

    @GetMapping("/add-card")
    public String addCard() {
        return "card/add-card";
    }

    @PostMapping("/add-card")
    public String addCard(@ModelAttribute CardAdd dto) {
        service.add(dto);
        return "redirect:/";
    }

    @PostMapping("/remove/{id}")
    public String removeById(@PathVariable int id) {
        service.removeById(id);
        return "redirect:/";
    }


}
