package org.example.controllers;

import org.example.dbServices.DBCardService;
import org.example.models.CardMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cardsMenu")
public class CardMenuController {
    private final DBCardService dbCardService;

    public CardMenuController(DBCardService dbCardService) {
        this.dbCardService = dbCardService;
    }

    @GetMapping()
    public String registerGet(HttpServletRequest request){
        String response = new CardMenuService().cardMenuMake(request, dbCardService);
        return response;
    }
    @PostMapping()
    public String registerPost(){
        return "cardsMenu";
    }
}
