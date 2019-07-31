package ru.itpark.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itpark.finalproject.domain.User;
import ru.itpark.finalproject.service.CardService;
import ru.itpark.finalproject.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private  final CardService cardService;

    @GetMapping("/personalArea")
    public String personalPage(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", service.loadUserByUsername(user.getUsername()));
        model.addAttribute("usercards", cardService.findUserCards(user.getId()));
        return "user/personalArea";
    }
}
