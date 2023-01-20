package com.example.task_mamager.controllers;


import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@AllArgsConstructor
@Controller
public class LandingController {

  private final PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String showLanding(){

        return "index";
    }
}
