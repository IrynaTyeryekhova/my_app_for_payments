package org.example.controllers;

import org.example.entities.Client;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping()
    public String login(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "indexLogin";
        return "redirect:/indexRegister";
    }


}
