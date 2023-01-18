package com.example.task_mamager.controllers;


import com.example.task_mamager.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
    public class AuthenticationController {

    private final UserService userService;


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }


    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "accessDenied";
    }
    }

