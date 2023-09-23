package org.example.controllers;

import org.example.dbServices.DBCardService;
import org.example.models.CardBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/cardBalanceChange")
public class CardBalanceChangeController extends HttpServlet {
//    public static final Logger LOG=Logger.getLogger(CardBalanceChange.class.getName());
    DBCardService cardService;
    @Autowired
    CardBalanceChangeController(DBCardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping()
    public String cardBalanceChange() { return "cardBalanceChange"; }

    @PostMapping()
    public String cardBalanceChangePost() { return "cardBalanceChange"; }

    @PatchMapping()
    public String cardBalanceChangePatch(HttpServletRequest request) throws ServletException, IOException {
        return new CardBalanceService().cardBalanceMake(request, cardService);
    }

}




