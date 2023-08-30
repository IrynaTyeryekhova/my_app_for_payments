package org.example.controllers;

import org.example.dbServices.DBClientService;
import org.example.entities.Client;
import org.example.models.LoginClientService;
import org.example.services.Service;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final DBClientService dbClientService;

    public LoginController(DBClientService dbClientService) {
        this.dbClientService = dbClientService;
    }

    @GetMapping()
    public String login(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()) return "indexLogin";
        String response = new LoginClientService().loginClientChecking(request, dbClientService, client);
        return response;
    }

    @PostMapping()
    public String loginPost(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()) {
            HttpSession session = request.getSession();
            String action = new Service().getParameter(session, request, "action");
            if(action.equals("login")) return "indexLogin";
            else return "personalOffice";
        }
        String response = new LoginClientService().loginClientChecking(request, dbClientService, client);
        return response;
    }
}
