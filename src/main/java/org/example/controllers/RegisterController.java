package org.example.controllers;

import org.example.dbServices.DBClientService;
import org.example.entities.Client;
import org.example.models.RegisterClientService;
import org.example.services.Service;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        if(bindingResult.hasErrors()) {
            HttpSession session = request.getSession();
            String action = new Service().getParameter(session, request, "action");
            if(action.equals("registration")) return "indexRegister";
            else return "personalOffice";
        }
        String response = new RegisterClientService().registerClientMake(request, dbClientService, client);
        return response;
    }

//    @GetMapping()
//    public String registerGet(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult, HttpServletRequest request){
//        if(bindingResult.hasErrors()) return "index";
//        String response = registerPost(client, bindingResult, request);
//        return response;
//    }
}
