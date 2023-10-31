package org.example.controllers;

import org.example.dbServices.DBCardService;
import org.example.dbServices.DBClientService;
import org.example.models.AdminCardMenuService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequestMapping("/adminCardsMenu")
public class AdminCardMenuController {
    DBCardService cardService;
    DBClientService clientService;

    AdminCardMenuController(DBCardService cardService, DBClientService clientService){
        this.cardService = cardService;
        this.clientService = clientService;
    }

    @GetMapping
    protected String adminCardMenuMakeGet(HttpServletRequest request) throws ServletException, IOException {
       return new AdminCardMenuService().adminCardMenuMake(request, cardService, clientService);
    }

    @PostMapping
    protected String adminCardMenuMakePost(HttpServletRequest request) throws ServletException, IOException {
        return new AdminCardMenuService().adminCardMenuMake(request, cardService, clientService);
    }
}
