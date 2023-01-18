package com.example.task_mamager.controllers;


import com.example.task_mamager.models.User;
import com.example.task_mamager.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@AllArgsConstructor
@Controller

public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public boolean isValidPassword(String password) {

        return password.length() >= 8 &&

                password.contains("0") ||
                password.contains("1") ||
                password.contains("3") ||
                password.contains("4") ||
                password.contains("5") ||
                password.contains("6") ||
                password.contains("7") ||
                password.contains("8") ||
                password.contains("9")
                        &&
                        password.contains("!") ||
                password.contains("#") ||
                password.contains("$") ||
                password.contains("%") ||
                password.contains("&") ||
                password.contains("*") ||
                password.contains("-") ||
                password.contains("_") ||
                password.contains("(") ||
                password.contains("+") ||
                password.contains(")") ||
                password.contains("=") ||
                password.contains("{") ||
                password.contains("}") ||
                password.contains("[") ||
                password.contains("]") ||
                password.contains(":") ||
                password.contains(";") ||
                password.contains("\"") ||
                password.contains("<") ||
                password.contains(">") ||
                password.contains(".") ||
                password.contains("?") ||
                password.contains("@") ||
                password.contains("\\") ||
                password.contains("'");


    }


    @GetMapping("/signup")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/signup")
    public String addUser(@Validated @ModelAttribute User user, BindingResult br, Model model, RedirectAttributes attributes) {
        String password = user.getPassword();

        if (br.hasErrors()) {
            return "register";
        }
        if (!isValidPassword(password)) {
            model.addAttribute("invalid", "Password must be 8 characters and above and must contain a special character and and at least 1 integer.");
                return "register";
        }


        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        userService.save(user);
        attributes.addFlashAttribute("success", "You are successfully registered! You can now login.");

        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

}
