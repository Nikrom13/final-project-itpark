package ru.itpark.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.finalproject.domain.User;
import ru.itpark.finalproject.dto.UserInfo;
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

    @GetMapping(params = "search")
    @RequestMapping("/userSearch")
    public String index(@RequestParam String search, Model model) {
        model.addAttribute("userSearch", search);
        model.addAttribute( "users", service.findByRequest(search));
        return "admin/user-list";
    }

    @GetMapping("/balance")
    public String balance(){

        return "user/personalArea";
    }

    @PostMapping("/change)")
    public String registrationCard(@ModelAttribute UserInfo data, Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = user.getId();
        model.addAttribute("user", user);
        service.changeProfileData(data);
        return "user/personalArea";
    }
}
