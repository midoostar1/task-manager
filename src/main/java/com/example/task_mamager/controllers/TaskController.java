package com.example.task_mamager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {
    @GetMapping("/task")
    public String showLanding(){
        return "tasks";
    }

}
