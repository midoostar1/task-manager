package com.example.task_mamager.controllers;


import com.example.task_mamager.models.User;
import com.example.task_mamager.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller

public class UserController {

  private final UserService userService;


    @GetMapping("/signup")
    public String showRegisterForm(Model model)
    {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/signup")
    public String addUser( @ModelAttribute User user){

        userService.save(user);
        return "redirect:/login";
    }

}
