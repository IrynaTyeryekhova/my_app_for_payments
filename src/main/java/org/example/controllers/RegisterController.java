package org.example.controllers;

import org.example.dbServices.DBClientService;
import org.example.entities.Client;
import org.example.models.RegisterClientService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final DBClientService dbClientService;

    public RegisterController(DBClientService dbClientService) {
        this.dbClientService = dbClientService;
    }

    @PostMapping()
    public String registerPost(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()) return "indexLogin";
        String response = new RegisterClientService().registerClientMake(request, dbClientService, client);
        return response;
    }

    @GetMapping()
    public String registerGet(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()) return "indexLogin";
        String response = registerPost(client, bindingResult, request);
        return response;
    }


}
