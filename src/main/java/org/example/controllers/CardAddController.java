package org.example.controllers;

import org.example.dbServices.DBCardService;
import org.example.entities.CardAccount;
import org.example.models.CardAddService;
import org.example.services.Service;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/cardAdd")
public class CardAddController {
    private final DBCardService dbCardService;

    public CardAddController(DBCardService dbCardService) {
        this.dbCardService = dbCardService;
    }

    @GetMapping("/new")
    public String cardAddGet(@ModelAttribute("cardAccount") CardAccount cardAccount){
        return "cardAdd";
    }

    @PostMapping("/new")
    public String cardAddPost(@ModelAttribute("cardAccount") CardAccount cardAccount){ return "cardAdd"; }

    @PostMapping ()
    public String cardAdd(@ModelAttribute("cardAccount") @Valid CardAccount cardAccount, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()) {
//            HttpSession session = request.getSession();
//            String action = new Service().getParameter(session, request, "action");
//            if(action.equals("languageChange")) return "redirect:/cardAdd/languageChange";
           return "cardAdd";
        }
        String response = new CardAddService().cardAdd(request, dbCardService, cardAccount);
        return response;
    }
}
