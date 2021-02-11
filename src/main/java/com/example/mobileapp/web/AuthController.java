package com.example.mobileapp.web;

import com.example.mobileapp.model.User;
import com.example.mobileapp.service.AuthService;
import com.example.mobileapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }



    @GetMapping("/register")
    public String register(Model model) {
        if (model.getAttribute("user") == null) {
            model.addAttribute(new User());
        }
        log.info("Model {}",model);
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


        authService.register(user);
       return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("username")) {
            model.addAttribute("username", "");
        }
        if (!model.containsAttribute("password")) {
            model.addAttribute("password", "");
        }

       return "auth-login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            @ModelAttribute("redirectUrl") String redirectUrl,
                            RedirectAttributes redirectAttributes,
                            HttpSession httpSession) {

        User logged = authService.login(username, password);
        if (logged == null) {
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("errors", "Invalid Username or Password");
            return "redirect:login";
        }

        httpSession.setAttribute("user", logged);

        if (redirectUrl == null || redirectUrl.trim().equals("")) {
            return "redirect:/";
        }

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/logout")
    String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
