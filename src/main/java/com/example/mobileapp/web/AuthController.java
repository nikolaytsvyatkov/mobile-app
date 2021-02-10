package com.example.mobileapp.web;

import com.example.mobileapp.model.User;
import com.example.mobileapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (model.getAttribute("user") == null) {
            model.addAttribute(new User());
        }
        return "auth-register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid @ModelAttribute User user,
                                   BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute("user", user);
            log.error("Error creating user {}",bindingResult.getAllErrors());
            return "redirect:register";
        }


        userService.createUser(user);
       return "redirect:login";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute User user) {
        System.out.println();
       return "auth-login";
    }
}
