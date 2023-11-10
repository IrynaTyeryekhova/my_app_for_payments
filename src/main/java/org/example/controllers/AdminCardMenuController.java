package org.example.controllers;

import org.example.dbServices.DBCardService;
import org.example.dbServices.DBClientService;
import org.example.entities.CardAccount;
import org.example.models.AdminBlockUnblockCardsService;
import org.example.models.AdminStatusChangeService;
import org.example.models.AdminUnlockCardsService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@RequestMapping("/adminCardsMenu")
public class AdminCardMenuController {
    DBCardService cardService;
    DBClientService clientService;

    AdminCardMenuController(DBCardService cardService, DBClientService clientService){
        this.cardService = cardService;
        this.clientService = clientService;
    }

//    @GetMapping
//    protected String adminCardMenuMakeGet(HttpServletRequest request) throws ServletException, IOException {
//       return new AdminCardMenuService().adminCardMenuMake(request, cardService, clientService);
//    }
//
//    @PostMapping
//    protected String adminCardMenuMakePost(HttpServletRequest request) throws ServletException, IOException {
//        return new AdminCardMenuService().adminCardMenuMake(request, cardService, clientService);
//    }
    @GetMapping
    public String adminCardMenuGet() { return "adminCardsMenu"; }

    @PostMapping
    public String adminCardMenuPost() {return "adminCardsMenu"; }

    @GetMapping("/requestsToUnlockCards")
    public String adminRequestsToUnlockCardsGet(HttpServletRequest request) {
        return new AdminUnlockCardsService().unlockCard(request, cardService);
    }
    @PostMapping("/requestsToUnlockCards")
    public String adminRequestsToUnlockCardsPost(HttpServletRequest request) {
        return new AdminUnlockCardsService().unlockCard(request, cardService);
    }

    @GetMapping("/blockUnblockCards")
    public String adminBlockUnblockCardsGet(HttpServletRequest request) {
        return new AdminBlockUnblockCardsService().blockUnblockCard(request, cardService);
    }

    @PostMapping("/blockUnblockCards")
    public String adminBlockUnblockCardsPost(HttpServletRequest request) {
        return new AdminBlockUnblockCardsService().blockUnblockCard(request, cardService);
    }

    @PostMapping ("/statusChangeRequestAdmin")
    public String adminRequestsToStatusChangePost(HttpServletRequest request) {
        return new AdminStatusChangeService().cardStatusChange(request, cardService);
    }
}
