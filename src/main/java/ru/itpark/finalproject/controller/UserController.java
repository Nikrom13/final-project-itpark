package ru.itpark.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/personalArea")
    public String personalPage(){
        return "user/personalArea";
    }
}
