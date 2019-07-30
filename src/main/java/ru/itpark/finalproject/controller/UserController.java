package ru.itpark.finalproject.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itpark.finalproject.domain.User;

@Controller
public class UserController {

    @GetMapping("/personalArea")
    public String personalPage(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "user/personalArea";
    }
}
