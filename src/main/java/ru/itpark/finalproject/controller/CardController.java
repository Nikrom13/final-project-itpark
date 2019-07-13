package ru.itpark.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.finalproject.domain.Card;
import ru.itpark.finalproject.domain.User;
import ru.itpark.finalproject.domain.UserCards;
import ru.itpark.finalproject.dto.CardAdd;
import ru.itpark.finalproject.service.CardService;

import java.util.Collection;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CardController {
    private final CardService service;

    @GetMapping("/card/{id}")
    public String getById(@PathVariable int id, Model model) {
        var card = service.findById(id);
        model.addAttribute("card", card);
        return "card/card";
    }

    @GetMapping("/edit/{id}")
    public String editById(@PathVariable int id, Model model){
        var card = service.findById(id);
        model.addAttribute(card);
        return "card/edit-page";
    }

    @PostMapping("/edit/{id}")
    public String editById(@PathVariable int id, @ModelAttribute CardAdd data){
        service.editById(id,data);
        return "redirect:/card/{id}";
    }

    @GetMapping("/back")
    public String back() {
        return "redirect:/";
    }

    @GetMapping("/reg-card/{id}")
    public String regCard(@PathVariable int id,Model model) {
        var card = service.findById(id);
        model.addAttribute("card", card);
        return "card/reg-card";
    }

    @PostMapping("/reg-card/{id}")
    public String registrationCard(@PathVariable int id){
        var card = service.findById(id);
        service.registerCard(card);
        return "redirect:/";
    }

    @GetMapping("/user-cards")
    public String userCards(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("usercards", service.findUserCards(user.getId()));
        return "user/user-cards";
    }

}
